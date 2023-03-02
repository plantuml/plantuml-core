# PlantUML going serverless
Up to now, you need either a local installation of PlantUML or a server (remote or local) to be able to generate diagrams.

Thanks for [CheerpJ](https://docs.leaningtech.com/cheerpj/), we have published a pure Javascript implementation of PlantUML.

This implementation runs within [a simple browser](https://plantuml.github.io/plantuml-core/raw.html).

## What is CheerpJ?

[CheerpJ](https://docs.leaningtech.com/cheerpj/) is a Java bytecode to WebAssembly and JavaScript compiler, compatible with 100% of Java, which allows to compile any Java SE application, library or Java applet into a WebAssembly/JavaScript application.

We are going to use the two following components from [CheerpJ](https://docs.leaningtech.com/cheerpj/):

  * The CheerpJ AOT compiler, an LLVM-based Java-bytecode to JavaScript compiler. This can be used to convert Java archives (e.g. `plantuml-core.jar`) files to JavaScript.
  * The CheerpJ runtime library, a full Java SE runtime in WebAssembly and JavaScript, that can be distributed in part or in full with applications converted with CheerpJ.

Note that it does not work with a local file (with double-clicking on the file). Files must be served by some webserver (even if it is a local one).

## How it works?

The plantuml-core project provides a dedicated version of PlantUML that can be compiled to Javascript with CheerpJ. The jar file `plantuml-core.jar` can be compiled to javascript with the simple command:

```
cheerpj_2.3/cheerpjfy.py --deps cheerpj_2.3/cheerpj-dom.jar plantuml-core.jar
```

You can get both files `plantuml-core.jar` and `plantuml-core.jar.js` [from Github](https://github.com/plantuml/plantuml-core/releases).

## Online demo for the impatient

All those pages are intentionally as simple as possible, so that people could quickly understand how they work and develop their own libraries:

  * [Using a raw canvas](https://plantuml.github.io/plantuml-core/raw.html), the fastest solution (same [in dark mode](https://plantuml.github.io/plantuml-core/raw-dark.html))
  * [Generating SVG](https://plantuml.github.io/plantuml-core/svg.html) (same in [dark mode](https://plantuml.github.io/plantuml-core/svg-dark.html))
  * [Generating PNG](https://plantuml.github.io/plantuml-core/png.html) (same in [dark mode](https://plantuml.github.io/plantuml-core/png-dark.html))


## Javascript API
### SVG generation

First, you have to load the runtime provided by Leaning Technology:

```
<script src="https://cjrtnc.leaningtech.com/2.3/loader.js"></script>
```

Then, you have to init PlantUML itself:

```
cheerpjRunMain("com.plantuml.api.cheerpj.v1.RunInit", "/app/plantuml-core.jar")
```

The `/app/` is a [virtual file-system that is the root of your webserver](https://docs.leaningtech.com/cheerpj/File-System-support). In the previous example, it means that both files `plantuml-core.jar` and `plantuml-core.jar.js` must be stored at the root of your server (so something like `http://127.0.0.1:8080/plantuml-core.jar.js`).

Finally, you can get the SVG version of any diagram:

```
cjCall("com.plantuml.api.cheerpj.v1.Svg", "convert", "light", text);
```

where:

  * `"light"`: could be either `dark` or `light` depending of the theme you want to use.
  * `text`: String that contains the diagram source text.

