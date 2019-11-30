package m.main

import java.nio.file.Files
import java.nio.file.Paths

class GenerateHtml
{
    def static void main(String[] args)
    {
        println('Generating HTML')
        Files.write(Paths.get('m.html'),'Hello World!'.bytes)
    }
}