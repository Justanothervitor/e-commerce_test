import React from "react"
import UserServices from "../../service/UserServices"

class LoginComponent extends React.Component
{
    constructor(props)
    {
        super(props)
    
        this.state = 
        {
            input : {},
            errors:{}
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event)
    {
        let input = this.state.input;
        input[event.target.name] = event.target.value;
        this.setState({input})
    }

    handleSubmit(event)
    {
        event.preventDefault();
        UserServices.login();
        if(this.validate())
        {
            console.log(this.state);

            let input = {};
            input["email"] = "";
            input["password"] = "";
            this.setState({input:input});
        }
    }

    validate()
    {
        let input = this.state.input;
        let errors = {} ;
        let isValid = true;
    
        if(!input["email"]){
            isValid = false;
            errors["email"] = "Please inform a valid email";
        }

        if(typeof input["email"] != "undefined")
        {
            var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
            if(pattern.test(input["email"]))
            {
                isValid = false;
                errors["email"] = "Please inform a valid email";
            }
        }

        if(!input["password"]){
            isValid = false;
            errors["password"] = "Please enter your password"
          }
    
    
          if(typeof input["password"] !== "undefined"){
            var patternPassword = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(!=.*[0-9])(!=.*[!@#$%^&*])(!=.{8,})");
            if(!patternPassword.test(input["password"])){
              isValid = false;
              errors["password"] = "The password should contain atleast one lowercase, one uppercase, one digit and one special character. The password should be atleast 8 characters long."
            }
          }
          this.setState({errors:errors});
          return isValid;
    }

    render(){
        return(
            <div>
                <h1>Login</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        <input type="text" name = "email" value={this.state.input.email} placeholder="enter your email" id="email"></input>
                    </label>
                    <label>
                    <input type="password" name = "password" value={this.state.input.password} placeholder="enter your password" id="password"></input>
                    </label>
                    <label>
                        <input type="submit" value="SUBMIT"/>
                    </label>
                </form>
            </div>
        );
    }


}

export default LoginComponent;