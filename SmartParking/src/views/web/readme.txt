Webpage para efetuar um pedido de um lugar de estacionamento à plataforma SmartParking.

Configuração:
- Transferir e Instalar Tomcat.
- Transferir JDBC Driver e Colocar na pasta \lib do Tomcat.
- Copiar o ficheiro SmartParking.war para a pasta \webapps do Tomcat.
- No browser colocar o link http://localhost:8080/SmartParking.

Atualização do Ficheiro WAR (Caso se faça alterações):
- Abrir linha de comandos.
- Ir a pasta \src\views do Projeto Intellij SmartParking.
- jar cfv SmartParking.war -C web .