import React from 'react'
import ProductServices from '.../service/ProductServices'

class ProductCreationComponent extends React.Component {

    constructor(props){
        super(props)
        
        this.state ={
            productId: this.props.match.params.productId,
            productName: '',
            description: '',
            linkToImage: '',
            price: ''
        }
        this.changeProductNameHandler = this.changeProductNameHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.changeLinkToImageHandler = this.changeLinkToImageHandler.bind(this);
        this.changePriceHandler = this.changePriceHandler.bind(this);
        this.saveOrUpdateProduct = this.saveOrUpdateProduct.bind(this);
    }

    componentDidMount()
    {
        if(this.state.productId === '_add')
        {
            return
        }else{
            ProductServices.getById(this.state.productId).then((res)=>{
                let product = res.data;
                this.setState({
                    productName : product.productName,
                    description : product.description,
                    linkToImage : product.linkToImage,
                    price : product.price
                });
            });
        }
    }

    saveOrUpdateProduct = (e) =>{
        e.preventDefault();
        let product = {productName: this.state.productName,
        description: this.state.description,
        linkToImage: this.state.linkToImage,
        price : this.state.price};
        console.log('product =>' + JSON.stringify(product));

        if(this.state.id === '_add'){
            ProductServices.insert(product).then((res)=>{
                this.props.history.push('/products');
            });
        }else{
            ProductServices.update(product, this.state.productId).then((res)=>{
                this.props.history.push('/products');
            });
        }
    }

    changeProductNameHandler = (event) => {
        this.setState({productName: event.target.value});
    }

    changeDescriptionHandler = (event) => {
        this.setState({description: event.target.value});
    }

    changeLinkToImageHandler = (event) => {
        this.setState({linkToImage: event.target.value});
    }
    
    changePriceHandler = (event) => {
        this.setState({price: event.target.value});
    }

    cancel()
    {
        this.props.history.push('/products');
    }

    getTittle()
    {
        if(this.state.id === '_add')
        {
            return<h3>Adicionar Produto</h3>
        }else{
            return <h3>Atualizar o Produto</h3>
        }
    }

    render()
    {
        return(
            <div>
                <br></br>
                <div>
                    <div>
                        <div>
                            {
                            this.getTittle()
                            }
                            <div>
                                <form>
                                    <div>
                                        <label>Nome do Produto:</label>
                                        <input placeholder='Nome do Produto' name='productName' value={this.state.productName}
                                        onChange={this.state.productName}/>
                                    </div>
                                    <div>
                                        <label>Descrição:</label>
                                        <input placeholder='Descrição' name='description' value={this.state.description}
                                        onChange={this.state.description}/>
                                    </div>
                                    <div>
                                        <label>Link para imagem:</label>
                                        <input placeholder='Link para a imagem do produto' name='linkToImage' value={this.state.linkToImage}
                                        onChange={this.state.linkToImage}/>
                                    </div>
                                    <div>
                                        <label>Preço:</label>
                                        <input placeholder='Preço do produto' name='price' value={this.state.price}
                                        onChange={this.state.price}/>
                                    </div>
                                    <button onClick={this.saveOrUpdateProduct}>Salvar o Produto</button>
                                    <button onClick={this.cancel.bind(this)}>Cancelar</button>
                                    <div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
export default ProductCreationComponent