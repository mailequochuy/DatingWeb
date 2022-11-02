/**
 * 
 */



var app = new Vue({
    el: '#app',
    data: {
        emailErrorInput: "",
        passwordErrorInput: "",
        email: null,
        password: null,
        password_length: 0,
        isShow: false,
        code: null,
        codeErrorInput: "",
        textforget: "Forget password",
        isShowLogin: false,
    },
    methods: {
        checkForm: function(e) {
            if (!this.email) {
                this.emailErrorInput = "Email required";
            } else if (!this.validEmail(this.email)) {
                this.emailErrorInput = "Unvalid email";
            } else {
                this.emailErrorInput = "";
            }

            if (this.emailErrorInput == "") {
                const data = {
                    email: this.email
                }
                  var _this = this;
                axios.post('/getcode', data )
                  .then(function (response) {
                    if(response.status == 200){
                        if(response.data == ''){
                            _this.emailErrorInput = "Email dose not exists!";
                        }else{
                            _this.isShow = true;
                            _this.textforget = "Sent code,please check email !"
                        }
                    };
                  });
                //this.isShow = true;
                //this.textforget = "Sent code, check email please!"
                //return true;
            }

            e.preventDefault();
        },
        validEmail: function(email) {
            var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        },
        getPassword: function(){
            if (!this.password) {
                this.passwordErrorInput = "Password required";
            } else {
                this.password_length = this.password.length;
                if (this.password_length < 6) {
                    this.passwordErrorInput = "At least 6 characters!";
                } else {
                    this.passwordErrorInput = "";
                }
            }
            
            if (!this.code) {
                this.codeErrorInput = "Code required";
            } else {
                this.code_length = this.code.length;
                if (this.code_length < 6) {
                    this.codeErrorInput = "At least 6 characters!";
                } else {
                    this.codeErrorInput = "";
                }
            }

            if (this.passwordErrorInput == "" && this.codeErrorInput == "") {
                const data = {
                    email: this.email,
                    code: this.code,
                    password: this.password
                }
                var _this = this;
                axios.post('/getpassword', data )
                  .then(function (response) {
                    if(response.status == 200){
                        console.log("done")
                        if(response.data == ''){
                            _this.codeErrorInput = "Wrong code, please check again!";
                        }else{
                            _this.textforget = "Change password successfully!";
                            _this.isShowLogin = true;
                            _this.isShow = false;
                        }
                    };
                  });
            }
        }
    }
})