import json
import math
import os
import re
import string

from models import Item, Version
from tfidf_calculator import compute_tfidf

DATA_DIR = os.path.join(
    os.path.dirname(os.path.abspath(__file__)),
    "..", "ShakesVis", "ShakesVis", "src", "data",
)

STANDARD_FILES = [
    "1604 BaseText Shakespeare.txt",
    "1832 Baudissin ed Wenig.txt",
    "1920 Gundolf.txt",
    "1941 Schwarz.txt",
    "1947 Baudissin ed Brunner.txt",
    "1952 Flatter.txt",
    "1962 Schroeder.txt",
    "1963 Rothe.txt",
    "1970 Fried.txt",
    "1973 Lauterbach.txt",
    "1976 Engler.txt",
    "1978 Laube.txt",
    "1985 Bolte Hamblock.txt",
    "1992 Motschach.txt",
    "1995 Guenther.txt",
    "2003 Zaimoglu.txt",
]

LEMMA_FILES = [
    "1604 BaseText Shakespeare.txt",
    "1832 Baudissin ed Wenig-Lemma.txt",
    "1920 Gundolf-Lemma.txt",
    "1941 Schwarz-Lemma.txt",
    "1947 Baudissin ed Brunner-Lemma.txt",
    "1952 Flatter-Lemma.txt",
    "1962 Schroeder-Lemma.txt",
    "1963 Rothe-Lemma.txt",
    "1970 Fried-Lemma.txt",
    "1973 Lauterbach-Lemma.txt",
    "1976 Engler-Lemma.txt",
    "1978 Laube-Lemma.txt",
    "1985 Bolte Hamblock-Lemma.txt",
    "1992 Motschach-Lemma.txt",
    "1995 Guenther-Lemma.txt",
    "2003 Zaimoglu-Lemma.txt",
]

PUNCT_RE = re.compile("[" + re.escape(string.punctuation) + "]")


def read_one_file(file_path):
    freq = {}
    with open(file_path, encoding="utf-8-sig") as f:
        line_count = 0
        for line in f:
            line = line.strip()
            if not line:
                continue
            if line_count == 0:
                line_count += 1
                continue
            processed = PUNCT_RE.sub("", line.lower()).replace("--", "")
            words = processed.split()
            for w in words:
                if w:
                    freq[w] = freq.get(w, 0) + 1
    return freq


def sort_frequency(freq_dict):
    return sorted(freq_dict.items(), key=lambda x: x[1], reverse=True)


def calculate_color(frequency, alpha=1.0):
    r = (math.sin(0.22 * frequency + 0) * 127 + 128) / 255
    g = (math.sin(0.22 * frequency + 2) * 127 + 128) / 255
    b = (math.sin(0.22 * frequency + 4) * 127 + 128) / 255
    return {"r": round(r, 4), "g": round(g, 4), "b": round(b, 4), "a": alpha}


def load_translations():
    path = os.path.join(DATA_DIR, "English-German.json")
    with open(path, encoding="utf-8-sig") as f:
        return json.load(f)


def build_version(file_name, sorted_freq, translations, is_base):
    name = file_name
    author = file_name[5:-4] if len(file_name) > 9 else file_name
    year = int(file_name[:4]) if file_name[:4].isdigit() else 0

    version = Version(name=name, author=author, year=year, is_base=is_base)

    for token, freq in sorted_freq:
        item = Item(
            token=token,
            frequency=freq,
            value=freq,
            color=calculate_color(freq),
        )
        if is_base and translations:
            trans = translations.get(token, [])
            item.translations = trans
        version.items.append(item)

    return version


def build_version_tfidf(file_name, tfidf_dict, orig_freq):
    sorted_tfidf = sorted(tfidf_dict.items(), key=lambda x: x[1], reverse=True)

    name = file_name
    author = file_name[5:-4] if len(file_name) > 9 else file_name
    year = int(file_name[:4]) if file_name[:4].isdigit() else 0

    version = Version(name=name, author=author, year=year, is_base=False)

    for token, tfidf_val in sorted_tfidf:
        freq = orig_freq.get(token, 0)
        item = Item(
            token=token,
            frequency=freq,
            tfidf=tfidf_val,
            value=tfidf_val,
            color=calculate_color(tfidf_val),
        )
        version.items.append(item)

    return version


def process_all(use_lemma=False, use_tfidf=False):
    files = LEMMA_FILES if use_lemma else STANDARD_FILES
    translations = load_translations()
    versions = []
    color_legend_map = {}

    if not use_tfidf:
        for i, fname in enumerate(files):
            path = os.path.join(DATA_DIR, fname)
            freq = read_one_file(path)
            sorted_freq = sort_frequency(freq)
            is_base = (i == 0)
            version = build_version(fname, sorted_freq, translations, is_base)
            versions.append(version)

            for item in version.items:
                if item.frequency not in color_legend_map:
                    color_legend_map[item.frequency] = item.color
    else:
        base_path = os.path.join(DATA_DIR, files[0])
        base_freq = read_one_file(base_path)
        base_sorted = sort_frequency(base_freq)
        base_version = build_version(files[0], base_sorted, translations, True)
        versions.append(base_version)

        for item in base_version.items:
            if item.frequency not in color_legend_map:
                color_legend_map[item.frequency] = item.color

        german_freqs = []
        german_files = []
        for i in range(1, len(files)):
            path = os.path.join(DATA_DIR, files[i])
            freq = read_one_file(path)
            german_freqs.append(freq)
            german_files.append(files[i])

        tfidf_results = compute_tfidf(german_freqs)

        for i, tfidf_dict in enumerate(tfidf_results):
            version = build_version_tfidf(german_files[i], tfidf_dict, german_freqs[i])
            versions.append(version)

            for item in version.items:
                if item.value not in color_legend_map:
                    color_legend_map[item.value] = item.color

    color_legend = sorted(
        [{"value": k, "color": v} for k, v in color_legend_map.items()],
        key=lambda x: x["value"],
        reverse=True,
    )

    return {
        "versions": [v.to_dict() for v in versions],
        "color_legend": color_legend,
    }
