# NetBeans No Extension MIME Resolver Plugin

Return a proper mime type from a shebang line of a file if the file extension is empty. (e.g. myscript)

## Example

```sh
#!/usr/bin/env bash
#!/bin/bash
```

## Supported type

- sh (text/sh)
- bash (text/sh)
- ruby (text/x-ruby)
- python (text/x-python)
- perl (text/x-perl)
- node (text/javascript)
- groovy (text/x-groovy)

If you have some requests, please submit them to the github repository as new [issues](https://github.com/junichi11/netbeans-noext-mime-resolver/issues).

## Note

This pluign doesn't provide syntax highlighting, code completion, e.t.c.
Require plugins for specified mime-types. e.g. C/C++ plugin(sh, bash)

## License

The MIT license

Copyright (c) 2014 junichi11

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.