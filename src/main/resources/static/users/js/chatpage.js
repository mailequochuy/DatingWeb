/**
 * 
 */
var app = new Vue({
    el: '#app',
    mounted() {
            this.listFriends = listFriends;
            this.listMarked = listMarked;
            this.user = user;
            this.listLikeYou = listLikeYou;
            this.countLikes = this.listLikeYou.length -1 ;
        },
    data: {
        user: '',
        ava64: this.user.avatar,
        isHidden: true,
        isActive: true,
        isShowMessage: true,
        message: "Life is the flower for which love is the honey",
        usermessage: "",
        arraymessage: [],
        quotes: [
            'It is so easy to fall in love but hard to find someone who will catch you',
            'We are most alive when we are in love',
            'Life is the flower for which love is the honey',
            'There is only one happiness in this life, to love and be loved'
        ],
        selectedQuote: null,
        listFriends: [],
        indexcss: -1,
        userFriend: null,
        arrayfriendmessages: [],
        frmess: [],
        umess: [],
        stop: 0,
        isMarked: -1,
        listMarked: [],
        listLikeYou: [],
        countLikes: 0,
        isShowMessageSM: true,
        time: new Date(Date.now()).toLocaleTimeString().toString()
    },
    computed: {
    }
    ,
    methods: {
        showTime: function(){
            var _this = this;
            this.time = new Date(Date.now()).toLocaleTimeString.toString()
                setInterval(function(){
                _this.time = new Date(Date.now()).toLocaleTimeString().toString()
            },1000)
        }
        ,
        markedFriend: function(id){
            const data={
                id: id 
            };
            var _this = this;
            axios.post('/markedfriend', data)
                .then(function (response) {
                        if(response.status == 200){
                            console.log(response.data);
                            _this.listMarked = response.data
                            console.log(_this.listMarked);
                        };
                      });
        }
        ,
        hideFriend: function(index){
            this.listFriends.splice(index, 1);
            this.isShowMessage = true;
        },
        deleteFriend: function(friend_id, userName, index){
            if(confirm("Do you really want to delete "+userName+" ?")){
                const data={
                    friend_id: friend_id
                };
                var _this = this;
                axios.post('/delete', data)
                .then(function (response) {
                        if(response.status == 200){
                            if(response.data == false){
                                window.location.reload();
                            }else{
                                _this.listFriends.splice(index, 1);
                            }
                        };
                      });
            }
        },
        showMoreOptions: function() {
            this.isHidden = false;
            this.isActive = false;
        },
        showMoreOptionsSM: function() {
            this.isHidden = false;
            this.isShowMessageSM = false;
        },
        
        hideMoreOptions: function() {
            this.isHidden = true;
            this.isActive = true;
        },
        hideMoreOptionsSM: function(){
            this.isHidden = true;
            this.isShowMessageSM = true;
        }
        ,
        getMessage: function(userFriendId) {
            const data = {
                message: this.usermessage,
                friend_id: userFriendId,
            };
            this.usermessage = "";
            var _this = this;
            axios.post('/saveMessage', data )
            .then(function (response) {
                    if(response.status == 200){
                        if(response.data == false){
                                window.location.reload();
                            }else{
                        _this.arraymessage.push(data);
                        }
                    };
                  });
        },
        randomItem(items) {
            return items[Math.floor(Math.random() * items.length)];
        },
        showFriendMessage(index, friend){
            clearInterval(this.intervalF);
            this.userFriend = friend;
            this.isShowMessage = false;
            this.indexcss = index;
            var _this = this;
            axios.post('/checkSession').then(function (response) {
                if(response.data == false){
                    window.location.reload();
                }else{
                    axios.post('/getMess', friend)
                    .then(function (response) {
                        if(response.status == 200){
                            _this.arraymessage = response.data;
                        };
                      });
                }
            })
            
            this.intervalF = setInterval(function(){
                axios.post('/checkSession').then(function (response) {
                if(response.data == false){
                    window.location.reload();
                }else{
                    axios.post('/getMess', friend)
                    .then(function (response) {
                        if(response.status == 200){
                            _this.arraymessage = response.data;
                        };
                      });
                }
            })
            },1000);
        },
        showFriendMessagetest(index, friend){
            clearInterval(this.intervalF);
            this.userFriend = friend;
            this.isShowMessage = false;
            this.indexcss = index;
            var _this = this;
            axios.post('/getMessage', friend)
                .then(function (response) {
                        if(response.status == 200){
                            _this.arraymessage = [],
                        _this.umess = response.data;
                        _this.updateUserMess(_this.umess);
                        };
                      });
                axios.post('/getFriendMessage', friend)
                .then(function (response) {
                        if(response.status == 200){
                            _this.arrayfriendmessages = [],
                        _this.frmess = response.data;
                        _this.updateFriendMess(_this.frmess);
                        };
                      });
            this.intervalF = setInterval(function(){
                axios.post('/getMessage', friend)
                .then(function (response) {
                        if(response.status == 200){
                            _this.arraymessage = [],
                        _this.umess = response.data;
                        _this.updateUserMess(_this.umess);
                        };
                      });
                axios.post('/getFriendMessage', friend)
                .then(function (response) {
                        if(response.status == 200){
                            _this.arrayfriendmessages = [],
                        _this.frmess = response.data;
                        _this.updateFriendMess(_this.frmess);
                        };
                      });
            },2000);
        },
        updateUserMess: function(umess){
            this.arraymessage = this.umess;
        },
        updateFriendMess: function(frmess){
            this.arrayfriendmessages = this.frmess;
        },
        scrollToEnd: function () {
            var content = this.$refs.friendmessage;
            if(content != null){
                content.scrollTop = content.scrollHeight;
            }
        },
    },
    created() {
        var intervalF;
        console.log("created");
        this.selectedQuote = this.randomItem(this.quotes);
    },
      updated() {
            this.$nextTick(() => this.scrollToEnd());
       }
})