/**
 * 
 */
 
 
 
 Vue.component('modeleditcomponent',{
    props: ['user', 'userMess'],
        data() {
             return {
                age: '',
                phoneNumber: '',
                descriptions: '',
                gender: '',
            }
        },
    template: '<div class="modal-content">'
                    +'<div class="modal-header">'
                        +'<h5 class="modal-title text" id="exampleModalLabel">Edit profile {{userMess}}</h5>'
                        +'<button type="button" class="close" data-dismiss="modal"aria-label="Close">'
                            +'<span aria-hidden="true">&times;</span>'
                        +'</button>'
                    +'</div>'
                    +'<div class="modal-body">'
                        +'<div class="text-left">'
                         +' <div class="form-group">'
                            +'<label for="recipient-name" class="col-form-label text">Phone number: </label>'
                            +'<input v-model="user.phoneNumber" id="phoneNumber" name="phoneNumber"  type="text" class="form-control text" required/>'
                          +'</div>'
                         +'<div class="form-group">'
                            +'<label for="recipient-name" class="col-form-label  text">Age:</label>'
                           +' <select v-model="user.age">'
                                +' <option value="0">No age</option>'
                                +' <option :value="n+15" v-for="n in 80">{{n+15}}</option>'
                              +' </select>'
                         +' </div>'
                         +' <div class="form-group">'
                         +'   <label for="recipient-name" class="col-form-label text">Gender:</label>'
                            +'    '
                                +'<label class=" text"> <input value="Man" v-model="user.gender" name="gender" class="radio-gender" type="radio" id="gender"> <i> Man </i></label>'
                                                    
                            +' '
                            +'    <label class=" text"> <input value="Woman" v-model="user.gender" name="gender" class="radio-gender" type="radio" id="gender"> <i> Woman </i></label>'
                                                    
                            +' '
                            +'    <label class=" text"> <input value="Else" v-model="user.gender" name="gender" class="radio-gender" type="radio" id="gender"> <i> Else </i></label>'
                          +'</div>'
                          +'<div class="form-group">'
                            +'<label for="message-text" class="col-form-label text">Something about yourself:</label>'
                            +'<textarea v-model="user.descriptions"  id="descriptions" name="descriptions"  class="form-control text"></textarea>'
                          +'</div>'
                          +'<div class="modal-footer">'
                            +'<button @click="close" class="btn btn-secondary" data-dismiss="modal">Close</button>'
                            +'<button @click="editUser" data-dismiss="modal" class="btn btn-primary" >Save</button>'
                        +'</div>'
                       +' </div>'
                    +'</div>'
                +'</div>',
        
        methods: {
            editUser() {
                const data = {
                    age: this.user.age,
                    descriptions: this.user.descriptions,
                    phoneNumber: this.user.phoneNumber,
                    gender: this.user.gender
                  };
                  var _this = this;
                  axios.post('/checkSession').then(function (response) {
                    if(response.data == false){
                        window.location.reload();
                    }else{
                        axios.post('/updateProfile', data )
                          .then(function (response) {
                            if(response.status == 200){
                                _this.$emit('event_child', response.data);
                            };
                          });
                    }
                    })
            },
            close: function(){
                var _this = this;
                axios.post('/noUpdate', user)
                  .then(function (response) {
                    if(response.status == 200){if(response.data == ''){
                                window.location.reload();
                            }else{
                        _this.$emit('event_child', response.data);
                        }
                    };
                  });
            },
        }
    
})
 