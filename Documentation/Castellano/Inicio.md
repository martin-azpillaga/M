# M

[English version](/Documentation/English/Home.md) --
[Versión en castellano](/Documentation/Castellano/Inicio.md) --
[Euskarazko bertsioa](/Documentation/Euskara/Hasiera.md)

M es un lenguaje de programación orientado a datos creado para desarrolladores de juegos.

Incrementa la productividad reduciendo el tiempo de desarrollo *y* mantenimiento, mientras convierte todo el proceso en más ameno y entretenido.

La filosofía detrás de este proyecto se resume por la excelente cita:

>Todo debería ser tan simple como se pueda, pero no más simple

## Diferenciadores clave

* **M está orientado a datos**

    M promueve el diseño orientado a datos por defecto, un paradigma de programación que ha demostrado ser especialmente adecuando para el desarrollo de videojuegos.

    Esto mejora tanto la eficiencia como la flexibilidad del proyecto. [Aprenda cómo](/Documentation/Castellano/Diseño-orientado-a-datos.md).

* **M no es solo para programadores**

     Los programadores se sentirán en casa escribiendo documentos con una sintaxis basada en C que pueden ser editados en cualquier editor que implemente el 
     [protocolo de servidor de lenguajes](/Documentation/Castellano/Language-server-protocol.md).

    Los artistas, diseñadores y directivos de proyecto quizás prefieran el [editor visual onlline](/M/Visual-editor/) basado en bloques.

    Más aún, todas estas interfaces pueden ser
    [combinadas](/Documentation/Castellano/Interfaces.md) sin fricción, permitiendo a todos comunicar sus ideas mejor, a todo el equipo.

* **M se integra en tu workflow**

    M *no* compila el código a un binario ejecutable. En cambio, genera un proyecto de alta calidad en su 
    [motor de juegos favorito](/Documentation/Castellano/Motores-de-juego-soportados.md).

    Cualquiera puede inspeccionar el código generado, modificar valores y importar sus propias piezas de arte creadas en su programa de creación de contenido digital habitual.

    M no compite con las herramientas que conoce. Le provee de una herramienta adicional que seguramente mejore su camino como desarrollador.

---

## Comencemos

La manera más rápida de empezar es usando el editor de escritorio basado en Visual Studio Code:

1. Descargue el [último editor de escritorio](https://github.com/martin-azpillaga/M/releases) para su sistema operativo. No requiere instalación, simplemente extraiga la carpeta y ejecute el ejecutable.

2. Haga click en nuevo `File/New project`, nómbrelo `HolaM` y seleccione las interfaces de Texto, Bloques y Unity Hibrido.

3. Abra el fichero `HolaM/Texto/main.texto` y cree su primera entidad escribiendo:

    ```m
        la pelota tiene masa 10, posición 1 2 y apariencia de sprite.pelota.
    ```

4. Guarde el fichero. Felicidades, puede encontrar el proyecto generado en la carpeta `Unity Hibrido`.

Opcionalmente, le puede interesar probar el editor visual por bloques
[online](/M/Visual-editor/).

1. Pulse el botón `Abrir` y abra el archivo generado `HolaM/Bloques/principal.bloques`.  

2. Intente añadir un componente llamado fricción con valor de 10 usando el menú `Numeric`.

3. Presione el botón `Guardar` y sobreescriba `HolaM/Bloques/principal.bloques` con el fichero descargado.

4. Guarde el fichero desde el editor. La carpeta Texto y Unity Hibrido se sincronizarán e incorporarán los cambios introducidos.

## Conviértase en experto

Puede conventirse en un experto en M en una semana leyendo su completa  
[documentación](/Documentation/Castellano/Indice.md).

Si es nuevo en el diseño orientado a datos, se recomienda que primero lea los 
[conceptos clave](/Documentation/Castellano/Conceptos-clave.md) 
y entonces continúe con la  
[implmentación específica de M](/Documentation/Castellano/Api.md).

Adicionalmente, puede inspirarse en los 
[proyectos de ejemplo](https://github.com/martin-azpillaga/M/tree/gh-pages/Examples)
mantenidos en este repositorio.

---

## Estado del proyecto

El proyecto M ha estado en desarrollo activo desde mitades de 2017 y lleva más de 4000 horas de investigación y desarrollo hasta mitades de 2019.

El proyecto ha ido cambiando durante sus [varias versiones](https://github.com/martin-azpillaga/M/releases) y está lenta pero seguramente alcanzando un estado de estabilidad y completitud.

Todavía quedan varios [obstáculos importantes](https://github.com/martin-azpillaga/M/issues) que superar y mucho que pulir antes de que el proyecto se pueda considerar listo para producción.

El proyecto empezó a mantenerse en Github a mitades de 2019 y su historia previa no se encuentra online. Desde entonces, cualquiera puede estar al día de los próximos avances gracias al
[proyecto de kanban](https://github.com/martin-azpillaga/M/projects/1).

## Ayude en que M sea aún mejor

Si le gusta el proyecto puede ayudar colaborando, compartiendo o haciendo donaciones.

La manera más sencilla de colaborar es 
[creando una issue](https://github.com/martin-azpillaga/M/issues), tanto los reportes de bug como peticiones de funcionalidad son bienvenidos.

Si es un desarrollador y quiere ayudar expandiendo M, puede pedir acceso al repositorio privado donde se alberga el código fuente contactando el autor <martin@minim.games>.
La documentación completa del programa interno así como su API se encontrará en [el wiki](https://github.com/martin-azpillaga/MSource/wiki) del repositorio.

Finalmente, puede ayudar al éxito de este proyecto donando cualquier cantidad usando el sistema de donación integrado en Github. Simplemente haga click en el botón con corazón de sponsorship en la página principal del repositorio. Toda la recaudación se destinará exclusivamente a los costes del desarrollo del proyecto.

---

## Autores y licencias

Este proyecto ha sido desarrollado por 
[Martin Azpillaga Aldalur](https://martin-azpillaga.github.io/), 
un desarrollador de herramientas de software y matemático.

La implementación del lenguaje hubiera sido imposible sin el 
[framework Xtext](https://github.com/eclipse/xtext) 
desarrollado por the Eclipse foundation.
El framework Xtext se distribuye bajo la licencia
[Eclipse Public License 1.0](https://www.eclipse.org/legal/epl-v10.html).

Los editores de escritorio y el editor online están basados en el 
[framework Theia](https://www.theia-ide.org)
desarrollado por TypeFox. Theia se licencia bajo la 
[licencia Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0).

El editor visual utiliza el 
[framework Blockly](https://github.com/google/blockly) desarrollado por Google.
El framework Blockly se distribuye bajo la 
[licencia Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0).