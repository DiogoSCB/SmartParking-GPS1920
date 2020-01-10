# SmartParking-GPS1920

### Estrutura das pastas
1. SmartParking - Projeto InteliJ com a aplicação do administrador e website.
2. Database - Ficheiros da base de dados mysql.
3. TP G33 - Todos os documentos elaborados durante o desenvolvimento do projeto. Exportado do Google Drive.

### Como configurar o projeto.

1. Abrir o InteliJ e selecionar `File` -> `New` -> `Project from Version Control` -> `Git`.
2. No campo `URL` escrever `https://github.com/DiogoSCB/SmartParking-GPS1920.git`
3. No campo `Directory`, escolhem a diretoria onde querem guardar o projeto.
4. Clicar em `Clone`.
5. Quando o Clone tiver terminado, abrir a vista do projeto do lado esquerdo e navegar até SmartParking/src.
6. Clicar na pasta `src` com um clique direito e selecionar `Mark Directory As` -> `Sources Root`.
7. Parabéns, agora só falta configurar o JDK13 e o JavaFX13, podes ir beber um fino ou um chá.

#### JAVA JDK 13
1. Visitar a página da Oracle para fazer o download no JDK13 [aqui](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html)
2. Selecionar a versão Windows `dk-13.0.1_windows-x64_bin.zip` ou no caso de macOS/Linux `(não sei quê).tar.gz`
3. Descompactar a pasta para uma diretoria à vossa escolha.
4. Selecionar `File` -> `Project Structure` -> No lado esquerdo selecionar `SDKs` -> Clicar no simbolo `+` e escolher JDK.
5. Selecionar o caminho para a diretoria onde está a pasta `jdk-13.0.1` (Do ponto 3) e confirmar.
6. No lado esquerdo onde foi selecionado `SDKs`, selecionar agora `Project` e na primeira droplist selecionar o JDK que acabaram de adicionar.
7. Ainda em `Project`selecionar o `Project compiler output' como sendo a pasta 'out' do projeto (SmartParking/out).

#### JAVAFX 13
1. Visitar a página de download do JavaFX [aqui](https://gluonhq.com/products/javafx/)
2. Fazer scroll até à secção `Latest Release` e selecionar o JavaFX Windows SDK. (macOS/Linux selecionar (...) SDK).
3. Descompactar a pasta para uma diretoria à vossa escolha.
4. Selecionar `File` -> `Project Structure` -> No lado esquerdo selecionar `Libraries` -> Clicar no simbolo `+` e escolher Java.
5. Selecionar o caminho para a diretoria onde está a pasta que fizeram download no ponto 3, concretamente para a pasta `lib` que lá está.
6. Selecionar OK e confirmar que aparecem itens em `Classes`, `Sources` e `Native Library Locations`.
7. Clicar em OK e fechar.
8. Neste ponto deve ser possível clicar no ícone play que aparece na mesma linha em que está a `public void start(Stage primaryStage)` (Do lado esquerdo ao pé dos números das linhas).
9. Parabéns outra vez, desta vez podes beber dois finos ou beber um chá e comer um pastel de nada.
