import React from "react"
import ProductService from "../../service/ProductServices"

class ProductViewComponent extends React.Component
{
    constructor(props){
        super(props)
        this.state = {
            productId : this.props.params.productId,
            product : {}
        }
    }
    
    componentDidMount()
    {
        ProductService.getById(this.state.productId).then(res =>{
            this.setState({product: res.data});
        });
    }

    render()
    {
        return(
            <div>
                <br/>
                <div>
                    <div>
                        <div>
                            <label>Nome do Produto:</label>
                            <div>{this.state.product.productName}</div>
                        </div>
                    </div>
                    <div>
                        <label>Descrição do produto:</label>
                        <div>{this.state.product.description}</div>
                    </div>
                    <div>
                        <label>Link da Imagem:</label>
                        <div>{this.state.product.linkToImage}</div>
                    </div>
                    <div>
                        <label>Preço do Produto:</label>
                        <div>{this.state.product.price}</div>
                    </div>

                </div>
            </div>
        )
    }
}

export default ProductViewComponent