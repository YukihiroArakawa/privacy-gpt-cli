# privacy-gpt-cli

## usage
1. clone this repository
2. set `OPENAI_API_KEY` in your config file, such as `.zshrc`, `.bashrc`.
```
# your config file(.zshrc, .bashrc)
export OPEN_API_KEY=<your open ai api key>
```
3. build a jar file in the project root.
```terminal
$ ./gradlew jar
```
4. start interaction
```terminal
$ java -jar build/libs/privacy-gpt-cli.jar
```
5. type `exit` if you quit the interaction.
