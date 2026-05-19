import math


def compute_tfidf(documents):
    results = []
    num_docs = len(documents)

    for doc in documents:
        tfidf_dict = {}
        for token, freq in doc.items():
            tf = 1 + math.log(freq)
            n = sum(1 for d in documents if token in d)
            idf = math.log10(num_docs / n)
            tfidf_val = int(tf * idf * 10)
            tfidf_dict[token] = tfidf_val
        results.append(tfidf_dict)

    return results
