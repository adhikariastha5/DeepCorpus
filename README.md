# 🧠 DeepCorpus

A collection of NLP experiments exploring text similarity, autocomplete, and search — built from scratch using character n-grams, cosine similarity, and Apache Lucene.

## 📂 Projects

### 🔤 Auto-Complete (`auto-complete/`)
An autocomplete/spell-suggestion engine built in **Clojure** that predicts the closest dictionary words using character n-grams and cosine similarity.

**How it works:**
1. Loads 1,000 random words from a 10,000-word dictionary
2. Generates 2-gram, 3-gram, and 4-gram character sequences for each word
3. Builds count vectors from the n-gram frequencies
4. Compares user input against the dictionary using **cosine similarity**
5. Returns the top 5 most similar words

```
Input: "appp" → Suggestion: "apple" ✅
```

No neural networks. No transformers. Just linear algebra.

**Run it:**
```bash
cd auto-complete
lein run
```

### 🔍 Lucene Search (`clojure-lucene/`, `lucene-clj/`, `luceneindex/`)
Experiments with **Apache Lucene** full-text search in Clojure — indexing and querying text corpora.

### 📓 Notebooks
- `Basics.ipynb` — Jupyter notebook with foundational NLP concepts

### 📄 Research Papers
- `Problem_1- String similarity for auto complete suggestion..pdf` — The problem statement that inspired the auto-complete project
- `Nepali spelling checking system_Birodh_Santa.pdf` — Related work on Nepali language spell checking
- `Discussions.pdf` — Additional research discussion

### 📝 Notes
- `learn.txt` — Hand-written notes explaining the count-vector math with worked examples

## 🔑 Key Concepts
- **N-Grams** — Character-level feature extraction
- **Count Vectors** — Bag-of-ngrams representation
- **Cosine Similarity** — Measuring vector similarity
- **Fuzzy String Matching** — Finding approximate matches
- **Apache Lucene** — Full-text search indexing

## 💡 Why This Matters
We use LLMs so much these days that we've almost forgotten what AI actually is at its core. Before the era of "just prompt it," there was math — beautiful, elegant math that still powers the systems we rely on every day.

These same principles underpin:
- Spell checkers and search suggestions
- Document similarity and plagiarism detection
- Recommendation systems
- Modern text embeddings (the vectors that power semantic search)

## 🛠 Tech Stack
- **Clojure** — Functional programming for data processing
- **Leiningen** — Clojure build tool
- **Apache Lucene** — Full-text search engine
- **Python / Jupyter** — Notebook experiments

---

*Built during early NLP explorations. Revisited and documented in 2026.*
