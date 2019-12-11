Webpage para efetuar um pedido de um lugar de estacionamento à plataforma SmartParking.

Configuração:
- Para testar tudo é necessário ter a base de dados a funcionar.
- Transferir e Instalar Tomcat (http://mirrors.up.pt/pub/apache/tomcat/tomcat-9/v9.0.29/bin/apache-tomcat-9.0.29.exe).
- Transferir JDBC Driver (Podem encontra-lo na pasta lib do projeto Intellij) e Colocar na pasta \lib do Tomcat (Progamas\Apache Software Foundation\Tomcat\lib).
- Copiar o ficheiro SmartParking.war para a pasta \webapps do Tomcat (Progamas\Apache Software Foundation\Tomcat\webapps).
- No browser colocar o link http://localhost:8080/SmartParking.

Atualização do Ficheiro WAR (Caso se faça alterações):
- Abrir linha de comandos.
- Ir a pasta \src\views do Projeto Intellij SmartParking.
- jar cfv SmartParking.war -C web .