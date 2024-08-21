# empire

Empire Builder Application in Java Swing / FX

## macOS

In macOS kann man das Menu auch in der Systemleiste anzeigen.
Dazu muss man den Parameter bei Java setzen.

```shell
-Dapple.laf.useScreenMenuBar=true
```

Wenn man die Anwendung im Debugger startet, kann man diese Option (in Eclipe) als VM arguments übergeben.

Wenn man eine [Anwendung baut](https://developer.apple.com/library/mac/#documentation/java/conceptual/java14development/03-javadeployment/javadeployment.html), kann man diese Option im plist File definieren.

```xml
<key>Properties</key>
<dict>
    <key>apple.laf.useScreenMenuBar</key>
    <string>true</string>
    ...
</dict>
```
