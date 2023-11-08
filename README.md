# Desafio agenda_java

Projeto de um CRUD de uma agenda completa utilizando springboot. Para o banco de dados, por ser um projeto mais simples, eu utilizei o banco em memória H2 do proprio springboot. 
Além das validações disponibilizadas pelo proprio jakarta, eu criei mais uma camada de validação como um service que retorna mensagens de validações específicas.

Instalei a dependencia do _springdoc-openapi-starter-webmvc-ui_ para poder gerar um swagger e descrever as funções de cada rota com seus repectivos statuscode.

Como o junit e Mockito ja vem na dependencia test do springboot, criei testes unitários para o repository e o service da agenda.

## OBS:

Eu criei o Dockerfile na minha api, mas eu não pude gerar a imagem e rodar por que minha máquina só tem 4 gb ram apenas, e com os programas abertos não foi capaz de gerar as imagens.
Mas eu criei a configuração no arquivo e dps de fazer a build da image, eu iria rodar docker run -d -p 72:90 my-sring-api:1.0 para iniciar a imagem na porta determinada.
