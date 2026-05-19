import json
import os

from data_reader import process_all

OUTPUT_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), "static", "data")

MODES = {
    "frequency":       {"use_lemma": False, "use_tfidf": False},
    "tfidf":           {"use_lemma": False, "use_tfidf": True},
    "lemma_frequency": {"use_lemma": True,  "use_tfidf": False},
    "lemma_tfidf":     {"use_lemma": True,  "use_tfidf": True},
}


def main():
    os.makedirs(OUTPUT_DIR, exist_ok=True)

    for mode, params in MODES.items():
        print(f"Computing {mode}...")
        data = process_all(**params)
        out_path = os.path.join(OUTPUT_DIR, f"{mode}.json")
        with open(out_path, "w", encoding="utf-8") as f:
            json.dump(data, f, ensure_ascii=False)
        size_kb = os.path.getsize(out_path) / 1024
        print(f"  -> {out_path} ({size_kb:.1f} KB)")

    print("Done. All JSON files generated.")


if __name__ == "__main__":
    main()
