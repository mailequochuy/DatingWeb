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

            if (this.passwordErrorInput == "" && this.emailErrorInput == "") {
                return true;
            }

            e.preventDefault();
        },
        validEmail: function(email) {
            var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }
    }
})