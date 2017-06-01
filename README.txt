/****************************************************************/
Universidade Anhembi Morumbi

Projeto Integrado 3° Semestre - Ciência da Computação


Aplicativo: SOU Biblioteca. 

Obj: Simular um sistema de biblioteca com locação e devolução de livros e cadastro no acervo. 
Aluno pode logar e compartilhar no facebook.
Utilizando o banco de dados SQLITE para salvar informações de alunos e livros

IDE: Eclipse NEON.3 - JAVA 8
/****************************************************************/

How to use: 

Execute o 'Sou Biblioteca.jar' dentro da pasta do projeto. (Onde ele esta agora, para não ocorrer erro de busca de lib e do banco"

Para logar como adm digite a senha: 'ragnar' ao cliar no botão na parte inferior da janela 

Irá abrir a tela de administrador, adicione livros ao clicar na aba cadastrar e visualize no acervo. 

Para locar um livro a um aluno, na aba emprestimo coloque o RA do aluno e o ID do livro.
O ID você pode obter visualizando no acervo.
O mesmo procedimento é feito ao devolver o livro.

Para cadastrar sem o facebook:
Na tela inicial clique em cadastrar e preencha todas informações.

Ao iniciar um login, pelo facebook pela primeira vez será solciitado um RA e uma senha de acesso. 

Você pode realizar o login pelo facebook ou digitando o RA e a senha e clicando em LOGIN

Na tela de usuario estarão as informações de livros locados pelo aluno que esta logado. Selecione um livro da lista para saber quando ele foi locado e quantos dias faltam para devolução. 7 dias é o prazo calculado. 

Ao chegar em 1 dia, uma mensagem é exibida na tela para lembrar o aluno de devlver o livro. 
Passando dos sete dias, é calculado uma taxa de multa de R$ 2,00 por dia. 

No botão compartilhar você compartilha no facebook o nome do livro e do autor que esta lendo. Caso não tenha cadastrado seu facebook irá pedir um login para acesso. 

/****************************************************************/

O que falta: 

Criptografar senha de acesso e o token do facebook. 
Atualização de quantidade de livros ao ser locado. 
Melhorar o design 

