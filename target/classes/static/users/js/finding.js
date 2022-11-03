/**
 * 
 */
var app = new Vue(
    {
        el: '#app',
        data: {
                hobby: [],
                options: ['Game', 'Sing', 'Dance'],
                age: "",
                gender: "",
                message: "It's so easy to fall in love but hard to find someone who will catch you",
                list: [],
                quotes: [
                    'It is so easy to fall in love but hard to find someone who will catch you',
                    'We are most alive when we are in love',
                    'Life is the flower for which love is the honey',
                    'There is only one happiness in this life, to love and be loved'
                ],
                selectedQuote: null,
                selectedItem: 0,
                user_id: null,
                friend_id: null,
                name: '',
                success: true,
                show: true,
                minAge: 16,
                maxAge: 80,
                genderError: null,
                noList: false,
                noListMess: "Can't find any suitable friends ...",
                userss: '',
                ava64: this.userss.avatar,
                error: '',
                isShowFind: true,
        },
        mounted() {
            this.userss = userss;
        },
          computed: {
            sliderMin: {
              get: function() {
                var val = parseInt(this.minAge);
                return val;
              },
              set: function(val) {
                val = parseInt(val);
                if (val > this.maxAge) {
                  this.maxAge = val;
                }
                this.minAge = val;
              }
            },
            sliderMax: {
              get: function() {
                var val = parseInt(this.maxAge);
                return val;
              },
              set: function(val) {
                val = parseInt(val);
                if (val < this.minAge) {
                  this.minAge = val;
                }
                this.maxAge = val;
              }
            },
        },
        methods: {
            findding: function(){
                if(this.gender == ''){
                    this.genderError = 'Required'
                }else{
                    this.genderError = ''
                }
                
                if(this.genderError == ''){
                const data = {
                    gender: this.gender,
                    minAge: this.minAge,
                    maxAge: this.maxAge,
                };
                var _this = this;
                axios.post('/checkSession').then(function (response) {
                    if(response.data == false){
                        window.location.reload();
                    }else{
                    axios.post('checkAva').then(function (response){
                        if(response.data == false){
                            _this.error = 'Update your avatar to start find friends!'
                        }else{
                            _this.list = [];
                            _this.selectedItem = 0;
                            axios.post('/findFriends', data).then(function (response){
                                if(response.data == ''){
                                    _this.noList = true
                                    _this.isShowFind = false
                                }else{
                                    _this.noList = false
                                    _this.list = response.data
                                    _this.isShowFind = false
                                }
                            });
                        }
                    })
                    }
                });
                }
            },
            showFind: function(){
                this.isShowFind = true
            },
            randomItem (items) {
                      return items[Math.floor(Math.random()*items.length)];
            },
            like: function(user_id, index){
                const data = {
                    friend_id: user_id
                };
                var _this = this;
                axios.post('/checkSession').then(function (response) {
                    if(response.data == false){
                        console.log("false")
                        window.location.reload();
                    }else{
                        axios.post('/likefriend', data);
                    }
                });
                this.selectedItem = this.selectedItem + 1;
                if(this.selectedItem == this.list.length){
                    this.isShowFind = true;
                }
            },
            dislike: function(){
                this.selectedItem = this.selectedItem + 1;
                if(this.selectedItem == this.list.length){
                    this.isShowFind = true;
                }
            }
        },
        created() {
                this.selectedQuote = this.randomItem(this.quotes)
         }
    })