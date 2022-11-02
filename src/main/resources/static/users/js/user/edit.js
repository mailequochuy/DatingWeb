/**
 * 
 */
 
var app = new Vue({
    el: '#profile',
    data: {
        user: '',
        info: null,
        avatar: '',
        ava64: this.user.avatar,
        showSubmit: false
    },
    mounted() {
        this.user = user;
    },
    methods: {
        eventChild: function(userEmit){
            this.user = userEmit;
        },
        active: function(){
            var _this = this;
            axios.post('/checkSession').then(function (response) {
                if(response.data == false){
                    window.location.reload();
                }else{
                    axios.post('/active', _this.user)
                  .then(function (response) {
                    if(response.status == 200){
                        if(response.data == ''){
                                window.location.reload();
                            }else{
                        _this.user = response.data;
                        }
                    };
                  });
                }
            })
        },
        hideSubmit: function(){
            this.showSubmit = false
            this.ava64 = this.user.avatar
        }
        ,
        handleEvent: function(event){
            this.avatar = event.target.files[0];
            this.showSubmit = true;
            var _this = this;
            let formData = new FormData();
            formData.append('avatar', this.avatar);
            const data ={
                avatar: this.avatar
            }
            if(data.avatar == ''){
                return
            }
            axios.post('/checkSession').then(function (response) {
                if(response.data == false){
                    window.location.reload();
                }else{
                    axios.post('/previewImg', formData,
                  {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                  })
                  .then(function (response) {
                    _this.ava64 = response.data;
                  });
                }
            })
        }
        ,
        saveAvatar: function(){
            var _this = this;
            let formData = new FormData();
            formData.append('avatar', this.avatar);
            const data ={
                avatar: this.avatar
            }
            if(data.avatar == ''){
                return
            }
                axios.post('/saveavatar', formData,
                  {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                  })
                  .then(function (response) {
                    _this.user = response.data;
                    _this.ava64 = response.data.avatar;
                  });
            this.showSubmit = false
        }
    }
})