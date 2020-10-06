# M

[English version](/Documentation/English/Home.md) --
[Versión en castellano](/Documentation/Castellano/Inicio.md) --
[Euskarazko bertsioa](/Documentation/Euskara/Hasiera.md)

M bideo joko garatzaileentzako zuzendutako datuetara bideratutako programazio hizkuntza bat da.

Produktibitatea hobetzen du proiektuen garatze *eta* mantenu denbora hobetuz, sormen prozesu guztia entretenigarriagoa egiten duen bitartean.

Proiektuaren atzean dagoen filosofia ongi laburtzen du ondorengo esaldi ezagunak:

>Dena ahal bezain simplea izan beharko zen, baina ez simpleagoa

## Ezaugarri garrantzitsuenak

* **M datuei bideratuta dago**

    M-n egindako proiektuak datuei bideratutako paradigma jarraitzen dute, programatzeko metodologia berri bat, espezialki egokia bideo jokoen garapenerako.
    
    Honi esker, proiektuaren efizientzia, baita malgutasuna ere hobetzen da. [Ikasi nola](/Documentation/Castellano/Diseño-orientado-a-datos.md).

* **M ez da programatzaileentzat soilik**

    Programatzaileak etxea bezala sentituko dira C hizkuntzan oinarritutako sintaxi ezagunarekin programatzen,  
    [hizkuntza zerbitzarien protokoloa](/Documentation/Castellano/Language-server-protocol.md) onartzen duen edozein editore
    erabiliz.

    Artista, diseinatzaile eta proiektu buruek berriz ziurrenik nahiagoko dute blokeetan oinarritutako onine aurkitu daitekeen [editore bisuala](/M/Visual-editor/).

    Hare eta gehiago, bi modu haueta eta gehiago batera landu daitezke eta beraien arten [nahastu](/Documentation/Castellano/Interfaces.md). Honela, taldeko edonorentzat errazagoa izango da beraien ideiak talde guztiarekin partekatzea.

* **M zure lan egiteko modura moldatzen da**

    M-n sortutako kodea *ez* da fitxategi binarioetara kompilatzen. Izan ere, proiektu bat sortzen da zure gustokoen duzun [joko motorean](/Documentation/Castellano/Motores-de-juego-soportados.md).

    Edonor gai da eraikitako kodea ikusteko, balioak aldatzeko, eta zeure arte eta musika fitxategiak importatzeko.

    M-ek ez du ezagutzen dituzun erramintekin konpetitzen. Erraminta berri bat eskaintzen dizu, ziurrenik zure lan egiteko modua hobetuko duena.

---

## Has gaitezen

M erabiltzen hasteko modurik azkarrena Visual Studio Coden oinarritutako editorea erabiltzea da:

1. Deskargatu zure plataformarako [azkenengo editorearen bertsioa](https://github.com/martin-azpillaga/M/releases). Ez du instalazio beharrik, soilik ireki karpeta eta abiarazi programa.

2. Egin klik `File/New project` menuan, eta sortu `KaixoM` izeneko proiektua Testua, Blokeak eta Unity-Hibridoa interfazeak dituena.

3. Ireki el fichero `KaixoM/Testua/hasiera.testua` eta sortu zure lehenengo entitatea:

    ```m
        pilotak masa 10, posizioa 1 2 eta itxura sprite.pilota du.
    ```

4. Gorde fitxategia. Zorionak, sortutako proiektua `KaixoM/Unity hibridoa` karpetan aurkitu ahal dezakezu.

Aukeran, blokeetan oinarritutako editore bisuala erabil dezakezu
[online](/M/Visual-editor/).

1. Egin klik `Ireki` botoien eta aukeratu aurreko atalean sorturiko `KaixoM/Blokeak/hasiera.blokeak` fitxategia.  

2. Saia zaite entitateari frikzioa izeneako komponente bat gehitzen eta ezarri 10 bere balio bezala `Zenbakiak` menua erabiliz.

3. Egin klik `Gorde` botoian eta berridatzi `KaixoM/Blokeak/hasiera.blokeak` fitxategia.

4. Ireki fitxategia editorearekin eta gorde. Testua eta Unity Hibridoa karpetak aldaketa berrietara moldatuko dira automatikoki.

## Bihurtu zaitez experto

Aste bat nahikoa da M hizkuntzan experto bihurtzeko bere [documentazio](/Documentation/Castellano/Indice.md) osoa irakurrita.

Datuei bideratutako diseinua berria bada zeuretzat, lehenik 
[kontzeptu klabeak](/Documentation/Castellano/Conceptos-clave.md)
irakurtzea gomendatzen da eta ondoren
[Mren implementazio espezifikoarekin](/Documentation/Castellano/Api.md) 
jarraitu.

Gehigarri moduan, respositorio honetan aurki ditzakezun
[adibidezko proiektuak](https://github.com/martin-azpillaga/M/tree/gh-pages/Examples)
bhatu ditzazkezu.

---

## Proeiktuaren egoera

M proiektua 2017ko erdialdetik egon da garapen aktiboan eta 4000 ordutik gora daramatza ikerketa eta garapenari eskainita.

Proiektua askotan aldatu da itxuraz [bertsio desberdinetan](https://github.com/martin-azpillaga/M/releases) baina pixkanaka estabilitate eta osotasun egoera batera iristen doa.

Oraindik badira 
[oztopo garrantzitsuak](https://github.com/martin-azpillaga/M/issues)
gainditzeko eta detalle asko pulitzeko proiektua produkzioarako prest dagoela esan aurretik.

Proiektua 2019ko erdialdean igo zen Githubera eta beraz lehenagoko historia ezin da aztertu online. Ordundik aurrera, edonork ikus dezake proeiktuan emango diren hurrengo pausoak 
[kanbaneko proiektuan](https://github.com/martin-azpillaga/M/projects/1).

## Lagundu M oraindik eta hobeagoa bihurtzen

Proiektua gustatu bazaizu, kolaboratuz, partekatuz edo donazioak eginez lagundu ahal dezakezu.

Kolaboratzeko modu errazena 
[issue bat sortzea da](https://github.com/martin-azpillaga/M/issues).
Bai aurkitutako bugen azalpenak bai funtzionalitate eskaerak ongi etorriak dira.

Software garatzailea bazara eta M zabaltzen lagundu nahi baduzu, kodea mantentzen den repositoriorako sarrera eskatu ahal diezaiokezu proiektuaren autoreari <martin@minim.games>.
Programaren dokumentazio eta barneko APIa repositorioaren 
[wikian](https://github.com/martin-azpillaga/MSource/wiki)
azalduko dira.

Azkenik, proiektuaren arrakastan lagundu ahal dezakezu edozein diru kantitate Githuben integratutako donazio sistema erabiliz. Oso erraza da, egin klik bihotza duen sponsorship botoian repositorioaren aurreneko weborrian. Bildutako diru guztia proeiktuaren garapen kosteak mantentzeko zuzenduko da.

---

## Egileak eta lizentziak

Proiektu hau  
[Martin Azpillaga Aldalur](https://martin-azpillaga.github.io/), 
software erraminta garatzaile eta matematikariak sortu du.

Hizkuntzen implementazioa ezinezkoa izango zen  
[Xtext](https://github.com/eclipse/xtext) 
frameworka gabe.
Xtext frameworka Eclipse Foundationek mantentzen du
[Eclipse Public License 1.0](https://www.eclipse.org/legal/epl-v10.html)
lizentziapean.

Mahaigaineko editoreak eta online editorea 
[Theia](https://www.theia-ide.org)
frameworkean oinarrituta daude, Typefoxek mantendua. Theia 
[Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
lizentziapean zabaltzen da.

Editore bisualak Googlek garatutako  
[Blockly](https://github.com/google/blockly) frameworka erabiltzen du.
Blockly 
[Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
lizentziapean zabaltzen da.