[![N|Solid](http://i68.tinypic.com/a2vzth.png)](https://nodesource.com/products/nsolid)
>Desafio realizado pela ilhasoft. 


# Descrição do teste:

O OMDb possui uma API Rest de consulta de filmes. (http://www.omdbapi.com).

O objetivo é criar um app que possibilite o cadastro de filmes (inputando apenas o nome) e para cada filme que você cadastre será feita uma consulta a essa API do IMDB para buscar as suas informações, como pôster, gênero do filme, ano e o que você considerar relevante para o usuário.

Os filmes escolhidos devem ser salvos e carregados até mesmo quando o aplicativo for utilizado sem internet. Use a criatividade na forma de exibição da coleção dos filmes e também no detalhamento do mesmo.

### Considerações:

- Não é permitido copiar código ou pedir ajuda de outras pessoas;
- Não determinamos tempo fixo para finalizar o teste, porém consideramos como critério o equilíbrio entre tempo e qualidade.

### Código:

Tecnologias que devem ser utilizadas:
Java ou Kotlin

### Bibliotecas/Arquitetura:

No teste é livre o uso de bibliotecas e arquitetura de projeto, porém segue abaixo algumas que utilizamos e utilizando-as a nota na avaliação final aumenta. As bibliotecas/arquiteturas são:

- MVP (arquitetura);
- Data Binding;
- RxJava;
- Room Persistence;
- Retrofit;

O tempo está contando a partir da data de recebimento do teste.

# Estrutura do Projeto
Sendo usado padrão de projeto MVP (Model-View-Presenter)

 - Model: Pacote com os modelos de dados e persistência do dado no dispositivo.
 - Presenter: Pacote com as regras de negocio
 - View: Pacote com a responsabilidade de manipulação da View (Activitys, Fragments, Adapters).
 - Util - Pacote com classes utilitárias para ser usada no projeto
 - Tasks: Classes Tasks que são executadas para acessar e consumir serviços (webservice).

# Frameworks utilizados

 - SugarORM: Persistência no banco de dados SQLite (http://satyan.github.io/sugar/).
 - Butterknife: Para injetar as views (http://jakewharton.github.io/butterknife/).
 - Picasso: Para obter as imagens, criar caches em memória e em disco (http://square.github.io/picasso/).

# Imagens do Projeto
