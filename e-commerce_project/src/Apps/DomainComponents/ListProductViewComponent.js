import React from "react"
import ProductServices from "../../service/ProductServices"

class ListProductViewComponent extends React.Component
{
    constructor(props){
        super(props)
        
        this.state = {
            products : []
        }
    }

    getById(id)
    {
        this.props.history.push('/products/{id}');
    }

    componentDidMount()
    {
        ProductServices.getAll().then((res)=>{
            if(res.data== null)
            {
                this.props.history.push('/products/_add');
            }
            this.setState({products :res.data});
        });
    }

    render()
    {
        return(

            this.state.products.map(product =>(
                <nav className="produtos">
                <div className="bloco">
                    <img className="productImage" src={product.linkToImage} alt=""/>
                    <p>{product.productName}</p>
                    R${product.price}
                </div>
                </nav>
                )
               )
    )
    }
}
export default ListProductViewComponent