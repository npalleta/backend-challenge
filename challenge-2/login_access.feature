- Eu como testador, necessito realizar as seguintes validações:

# positivo
Funcionalidade: Realizar o acesso a plataforma utilizando dados de usuário

Cenário: Validar o acesso no sistema / aplicação
Dado que eu acesso a aplicação
Quando eu preencher o usuário utilizando o RG '12.123.123-1'
E eu preencher a senha com os últimos quatro dígitos do documento
Então o sistema / aplicação deverá apresentar a tela pós login

# negativo
Funcionalidade: Tentar realizar o acesso a plataforma utilizando um RG inválido

Cenário: Validar tratamento de erro - RG Inválido
Dado que eu acesso a aplicação
Quando eu preencher o usuário utilizando um RG inválido
E eu preencher a senha com os últimos quatro dígitos do documento
Então o usuário não poderá acessar a plataforma
E uma mensagem de erro RG inválido deverá ser apresentado

# negativo
Funcionalidade: Tentar realizar o acesso a plataforma utilizando um RG com caracteres especiais

Cenário: Validar tratamento de erro - RG com caracteres especiais
Dado que eu acesso a aplicação
Quando eu preencher o usuário utilizando um RG com caracteres especiais
E eu preencher a senha com os últimos quatro dígitos do documento
Então o usuário não poderá acessar a plataforma
E uma mensagem de erro RG inválido deverá ser apresentado

# negativo
Funcionalidade: Tentar realizar o acesso a plataforma utilizando uma senha inválida

Cenário: Validar tratamento de erro - Senha inválda
Dado que eu acesso a aplicação
Quando eu preencher o usuário utilizando o RG '123.123.123.12'
E eu preencher a senha de forma inválida
Então o usuário não poderá acessar a plataforma
E uma mensagem de erro senha inválida

# negativo
Funcionalidade: Tentar realizar o acesso a plataforma utilizando uma senha caracteres aleatórios

Cenário: Validar tratamento de erro - Senha com caracteres aleatórios
Dado que eu acesso a aplicação
Quando eu preencher o usuário utilizando o RG '12.123.123-1'
E eu preencher a senha com caracteres aleatórios
Então o usuário não poderá acessar a plataforma
E uma mensagem de erro senha inválida

# negativo
Funcionalidade: Validar limite do campo usuário

Cenário: Validar quantidade permitida de caracteres no campo usuário
Dado que eu acesso a aplicação
Quando eu preencher o usuário utilizando um RG com mais de 9 dígitos
Então o usuário não poderá acessar a plataforma

# negativo
Funcionalidade: Validar limite do campo senha

Cenário: Validar quantidade permitida de caracteres no campo usuário
Dado que eu acesso a aplicação
Quando eu preencher o usuário utilizando o RG '12.123.123-1'
E eu preencher a senha com mais de 4 dígitos
Então o campo de usuário deverá estar limitado a 4 caracteres

# positivo
Funcionalidade: Validar dados de login salvos em banco

Cenário: Validar dados do usuário em banco
Dado que eu acesso a base de dados
Quando eu executar a query utilizando o RG '12.123.123-1'
Então a query deverá me retornar os dados de login do usuário salvos em banco

# positivo
Funcionalidade: Verificar os dados de login do usuário cadastrados via API

Cenário: Validar dados do usuário cadastrados via API
Dado que eu acesso a api de consulta de dados de login
Quando eu colocar o token gerado à partir da api de autorização
Então a api deverá me retornar os dados de login do usuário