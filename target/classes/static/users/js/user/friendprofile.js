/**
 * 
 */
 
var app = new Vue({
    el: '#profile',
    data: {
        user: '',
        friend: '',
        frava64: this.friend.avatar,
        ava64: this.user.avatar
    },
    mounted() {
        this.user = user;
        this.friend = friend;
    },
    methods: {
        unFriend: function(id){
            if(confirm("Do you really want to delete "+this.friend.userName+" ?")){
                const data={
                    id: id
                };
                var _this = this;
                axios.post('/delete', data)
                .then(function (response) {
                        if(response.status == 200){
                            if(response.data == false){
                                window.location.reload();
                            }else{
                                window.location = "/chats"
                            }
                        };
                      });
            }
        }
    }
})