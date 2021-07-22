#language: pt
@realizarCompra
Funcionalidade: Realizar compra

  Contexto: 
    Dado que eu esteja na pagina de login
    E realize o login com o email "email@email.email" e senha "tester13"

  @validarValorCompra
  Cenário: Validar Valor de Compra no Carrinho
    Dado que eu tenha selecionado um item para o carrinho
    Quando eu clico no icone do carrinho
    E verifico a quantidade de item e o valor unitario do produto
    E verifico se a taxas pela compra
    E obtenho a soma de todos os valores
    Então o valor total apresentado no carrinho deve ser igual a soma dos produtos mais taxas
