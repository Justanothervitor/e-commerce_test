import React from 'react'
import APIService from '../service/APIService'

export default class ProductComponent extends React.Component {

    constructor(props) {
        super(props)
    
        this.state = {
             product: []
        }
    }
    
    componentDidMount(){
        APIService.getProducts().then((data) => {
            this.setState({ product: data })
            console.log(this.state.data)
          })
          .catch(function (ex) {
              console.log('Response parsing failed. Error: ', ex);
          });;
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Product Details</h2>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>Product Id</th>
                            <th>Product Name</th>
                            <th>Description</th>
                            <th>Link</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.product.map(product =>
                                    <tr key={product.productId}>
                                        <td>{product.productId}</td>
                                        <td>{product.prodcutName}</td>
                                        <td>{product.descripton}</td>
                                        <td>{product.linkToImage}</td>
                                        <td>{product.price}</td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}

