# Histórias de Usuário

## US1 - Cadastro de Professor

*Como* professor,  
*Quero* estar cadastrado no sistema,  
*Para* distribuir moedas aos alunos.  

**Critérios de aceitação:**
- O sistema deve receber a lista da instituição de ensino para cadastro de professores contendo nome, CPF e departamento vinculado.
- Cada professor deve receber um saldo inicial de 1.000 moedas acumulável semestralmente.
- O sistema deve verificar se o professor já está cadastrado, baseado no CPF e no departamento.

## US2 - Consulta de Extrato de Aluno

*Como* aluno,  
*Quero* consultar meu extrato,  
*Para* verificar quantas moedas tenho e as transações realizadas.  

**Critérios de aceitação:**
- O aluno deve poder visualizar o saldo total de moedas.
- O extrato deve listar todas as transações, incluindo recebimentos e trocas de moedas.
- O aluno deve ser notificado por email sempre que receber moedas.
- O sistema deve permitir o filtro por data e tipo de transação (recebimento ou troca).

## US3 - Notificação de Resgate para Empresa Parceira

*Como* empresa parceira,  
*Quero* receber notificações por email quando um aluno resgatar uma vantagem,  
*Para* acompanhar a utilização das minhas ofertas.  

**Critérios de aceitação:**
- A empresa deve ser notificada por email com um código de conferência quando um aluno resgatar uma vantagem.
- O email de notificação deve incluir detalhes do aluno, da vantagem resgatada, e um código gerado pelo sistema.
- O sistema deve garantir que apenas empresas cadastradas recebam notificações de resgate.
- As vantagens devem incluir descrição e foto do produto ou serviço ofertado.

## US4 - Distribuição de Moedas

*Como* professor,  
*Quero* distribuir moedas aos alunos,  
*Para* reconhecer e premiar o bom comportamento e participação em aula.  

**Critérios de aceitação:**
- O professor deve selecionar o aluno e o montante de moedas a ser distribuído.
- O professor deve inserir uma mensagem obrigatória explicando o motivo da premiação.
- O sistema deve verificar se o professor tem saldo suficiente para realizar a transação.
- O aluno deve ser notificado por email sobre o recebimento das moedas.

## US5 - Cadastro de Aluno

*Como* aluno,  
*Quero* me cadastrar no sistema,  
*Para* acessar minhas moedas e trocar por vantagens.  

**Critérios de aceitação:**
- O aluno deve fornecer nome, email, CPF, RG, endereço, instituição de ensino e curso.
- O aluno deve selecionar a instituição de ensino a partir de uma lista pré-cadastrada.
- O aluno deve criar um login e uma senha para acessar o sistema.
- O sistema deve enviar um email de confirmação para ativação da conta do aluno.

## US6 - Trocar Moedas por Vantagens

*Como* aluno,  
*Quero* trocar minhas moedas por vantagens,  
*Para* aproveitar benefícios como descontos em restaurantes e materiais de estudo.  

**Critérios de aceitação:**
- O aluno deve poder visualizar uma lista de vantagens disponíveis com o custo em moedas.
- O aluno deve poder filtrar as vantagens por categoria (ex.: alimentação, materiais, descontos).
- Após a troca, o saldo do aluno deve ser atualizado e ele deve receber um email com o cupom e código de conferência.
- A vantagem deve ser marcada como "resgatada" e o parceiro deve ser notificado.

## US7 - Cadastro de Vantagem para Empresa Parceira

*Como* empresa parceira,  
*Quero* cadastrar vantagens no sistema,  
*Para* oferecer benefícios aos alunos em troca de moedas.  

**Critérios de aceitação:**
- A empresa deve ser capaz de cadastrar uma vantagem com título, descrição, foto e custo em moedas.
- A empresa deve poder editar e remover vantagens já cadastradas.
- O sistema deve verificar se a vantagem cadastrada contém todos os campos obrigatórios.
- A vantagem cadastrada deve estar disponível para visualização por todos os alunos.

## US8 - Visualizar Extrato de Professor

*Como* professor,  
*Quero* visualizar meu extrato,  
*Para* acompanhar a quantidade de moedas que tenho e as transações que realizei.  

**Critérios de aceitação:**
- O professor deve poder visualizar seu saldo atual de moedas.
- O professor deve ter acesso a um histórico de transações de distribuição de moedas aos alunos.
- O extrato deve incluir data, aluno destinatário, quantidade de moedas e motivo da transação.
- O professor deve poder filtrar o extrato por período.

