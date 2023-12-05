import React from "react"
import ProductService from "../../service/ProductServices"

class ProductViewComponent extends React.Component
{
    constructor(props){
        super(props)
        this.state = {
            productId : this.props.match.params.productId,
            product : {}
        }
    }
    
    componentDidMount()
    {
        ProductService.getById(this.state.productId).then(res =>{
            if(res.data== null)
            {
                this.props.history.push('api/products/:id');
            }
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
                        <div><img src={this.state.product.linkToImage} alt=""/></div>
                    </div>
                    <div>
                        <label>Preço do Produto:</label>
                        <div>{this.state.price}</div>
                    </div>

                </div>
            </div>
                    )
    }
}

export default ProductViewComponent