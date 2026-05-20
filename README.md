# PlantUML going serverless

> ⚠️ **This project is discontinued.** The `plantuml-core` / CheerpJ approach has been abandoned in favour of a native JavaScript build. Please use the main PlantUML repository instead: **[https://github.com/plantuml/plantuml](https://github.com/plantuml/plantuml)**

---

PlantUML can now run **entirely in the browser** — no server, no local installation needed — thanks to a native JavaScript build using [TeaVM](https://github.com/konsoletyper/teavm) and [Viz.js](https://github.com/mdaines/viz-js).

## Demo editors

A collection of ready-to-use HTML editors is available at:
👉 **[https://plantuml.github.io/plantuml/js-plantuml/index-collection.html](https://plantuml.github.io/plantuml/js-plantuml/index-collection.html)**

Each page is a self-contained starting point for developers who want to build their own PlantUML-powered tools.

| Editor | Description |
|--------|-------------|
| [Playground](https://plantuml.github.io/plantuml/js-plantuml/index.html) | Full split-pane editor with live preview, copy and download |
| [Basic](https://plantuml.github.io/plantuml/js-plantuml/index-basic.html) | Minimal single-file skeleton, easy to hack |
| [Basic Dark](https://plantuml.github.io/plantuml/js-plantuml/index-basic-dark.html) | Same, with dark mode via `{dark: true}` rendering option |
| [GitHub PoC](https://plantuml.github.io/plantuml/js-plantuml/github-integration-poc.html) | Renders ` ```plantuml ` fenced blocks in Markdown (iframe + postMessage) |
| [GitHub PoC Worker](https://plantuml.github.io/plantuml/js-plantuml/github-integration-web-worker-poc.html) | Same with parallel rendering via hidden iframe workers |

