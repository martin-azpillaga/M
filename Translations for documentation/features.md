# Features

## Technical

### Language server

- Independent of language:

- Connect to a language client via stdin and stdout.
- Connect to a language client via hardcoded socket 5007.
- Announce server capabilities to client:
    Folder edits, Text edits, hover, completions and signature.
- Shutdown server when requested.
- Synchronize projects with folder edits. (Fails when removing the first folder)
- Synchronize project files with folder edits. (Add or remove files, files updated)
- Publish diagnostics to client when files get modified.

- In every project

- Detect syntax errors
- Detect validation errors (undefined, redefined, readonly)
- Extract all functions, components and values
- Report diagnostics to the client
- Incremental global validation

- Control generation with Ⲙ.json file

### Build system

- Linting
- Structure checks
- Build check
- VSCode packaging

## User value
