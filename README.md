# 🕸️ SpideyBot: Java Job Hunter

Um bot inteligente desenvolvido com **Java 21** e **Spring Boot 3** que realiza Web Scraping na Gupy para encontrar vagas de Java e envia notificações em tempo real via Telegram.

## Funcionalidades
- **Web Scraping Inteligente**: Consome a API da Gupy para buscar vagas recentes.
- **Filtros Personalizados**: Ignora vagas encerradas ou que não condizem com a stack de Java.
- **Persistência (Memória de Elefante)**: Utiliza **Spring Data JPA** e **H2 Database** para garantir que você nunca receba a mesma vaga duas vezes.
- **Agendamento Automático**: O bot trabalha sozinho em intervalos definidos (Spring Scheduler).
- **Notificações Push**: Links diretos para candidatura enviados via Telegram Bot API.

## Tecnologias Utilizadas
- **Java 21** & **Spring Boot 3**
- **JSoup**: Para requisições e processamento de dados.
- **Jackson**: Para parsing de JSON.
- **Spring Data JPA**: Para persistência de dados.
- **H2 Database**: Banco de dados leve para execução local.

## Como rodar o projeto
1. Clone o repositório.
2. No arquivo `src/main/resources/application.properties`, adicione seu `bot.token` e `bot.name` (obtidos via BotFather).
3. Execute a classe `BotApplication`.
4. Mande um `/start` para o seu bot no Telegram.

---
Desenvolvido por **Giovania Dantas** 
