# Wrong source maps for node-script?

Make a release build `{:target :node-library}` with `:simple` optimizations

```
~/c/0/source-map-repro (off) (master) npx shadow-cljs release main
shadow-cljs - config: .../source-map-repro/shadow-cljs.edn  cli version: 2.8.74  node: v10.16.3
[:main] Compiling ...
[:main] Build completed. (42 files, 3 compiled, 0 warnings, 28.67s)
```

Run it and note line numbers:
```
~/c/0/source-map-repro (off) (master) node out/main.js
Error: Whoops
    at Object.source_maps.core.something_that_throws (.../source-map-repro/out/main.js:2295:228)
    at .../source-map-repro/out/main.js:2295:328
    at .../source-map-repro/out/main.js:5:22
    at Object.<anonymous> (.../source-map-repro/out/main.js:9:3)
    at Module._compile (internal/modules/cjs/loader.js:778:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:789:10)
    at Module.load (internal/modules/cjs/loader.js:653:32)
    at tryModuleLoad (internal/modules/cjs/loader.js:593:12)
    at Function.Module._load (internal/modules/cjs/loader.js:585:3)
    at Function.Module.runMain (internal/modules/cjs/loader.js:831:12)
```

Look up source map locations (they seem wrong?)
```
~/c/0/source-map-repro (off) (master) clj sourcemap.clj out/main.js.map 2295 228
;; out/main.js.map line 2295 col 228
{:original "shadow/cljs/constants/main.js", :line 1, :column 1}
```
```
~/c/0/source-map-repro (off) (master) clj sourcemap.clj out/main.js.map 2295 328
;; out/main.js.map line 2295 col 328
{:original "shadow/cljs/constants/main.js", :line 1, :column 1}
```
```
~/c/0/source-map-repro (off) (master) clj sourcemap.clj out/main.js.map 5 22
;; out/main.js.map line 5 col 22
nil
```
```
~/c/0/source-map-repro (off) (master) clj sourcemap.clj out/main.js.map 9 3
;; out/main.js.map line 9 col 3
nil
```
