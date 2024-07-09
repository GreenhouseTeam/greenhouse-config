package dev.greenhouseteam.greenhouseconfig.test.lang;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import dev.greenhouseteam.greenhouseconfig.impl.lang.toml.TomlFileDocument;
import dev.greenhouseteam.greenhouseconfig.impl.lang.toml.TomlFileElement;
import dev.greenhouseteam.greenhouseconfig.impl.lang.toml.TomlFileTableHeader;
import dev.greenhouseteam.greenhouseconfig.impl.lang.toml.TomlFileValue;
import dev.greenhouseteam.greenhouseconfig.impl.lang.toml.TomlParser;

public class TomlParseTests {
    public static void main(String[] args) throws IOException {
        simpleTests();
    }

    private static void simpleTests() throws IOException {
        String source = """
            # You must change these to your own details.
            [package]
            name = "hello-wasm"
            description = "My first real attempt at creating something cool with web-sys!"
            version = "0.1.0"
            authors = ["Kneelawk <kneelawk@gmail.com>"]
            categories = ["wasm"]
            readme = "README.md"
            edition = "2018"
                        
            [lib]
            crate-type = ["cdylib"]
                        
            [profile.release]
            # This makes the compiled code faster and smaller, but it makes compiling slower,
            # so it's only enabled in release mode.
            lto = true
                        
            [features]
            # If you uncomment this line, it will enable `wee_alloc`:
            #default = ["wee_alloc"]
                        
            [dependencies]
            rand = "^0.8.2"
            getrandom = { version = "^0.2.1", features = ["js"] }
                        
            # The `wasm-bindgen` crate provides the bare minimum functionality needed
            # to interact with JavaScript.
            wasm-bindgen = "0.2.69"
                        
            # `wee_alloc` is a tiny allocator for wasm that is only ~1K in code size
            # compared to the default allocator's ~10K. However, it is slower than the default
            # allocator, so it's not enabled by default.
            wee_alloc = { version = "0.4.5", optional = true }
                        
            # The `web-sys` crate allows you to interact with the various browser APIs,
            # like the DOM.
            [dependencies.web-sys]
            version = "0.3.46"
            features = [
                'CanvasRenderingContext2d',
                'Document',
                'Element',
                'HtmlCanvasElement',
                'Performance',
                'Window',
                'console'
            ]
                        
            # The `console_error_panic_hook` crate provides better debugging of panics by
            # logging them with `console.error`. This is great for development, but requires
            # all the `std::fmt` and `std::panicking` infrastructure, so it's only enabled
            # in debug mode.
            [target."cfg(debug_assertions)".dependencies]
            console_error_panic_hook = "0.1.6"
                        
            # These crates are used for running unit tests.
            [dev-dependencies]
            wasm-bindgen-test = "0.3.19"
            futures = "0.3.8"
            js-sys = "0.3.46"
            wasm-bindgen-futures = "0.4.19"
            """;

        TomlParser parser = new TomlParser(new StringReader(source));

        System.out.println("Document:");
        TomlFileDocument document = parser.parse();
        for (TomlFileElement elem : document.elements()) {
            switch (elem) {
                case TomlFileTableHeader header -> {
                    System.out.println("  Header: " + Arrays.toString(header.path()));
                    System.out.println("    Array: " + header.array());
                    System.out.println("    Comments: " + Arrays.toString(header.comments()));
                }
                case TomlFileValue value -> {
                    System.out.println("  Value: " + Arrays.toString(value.path()));
                    System.out.println("    Value: " + value.value());
                    System.out.println("    Comments: " + Arrays.toString(value.comments()));
                }
            }
        }
    }
}
