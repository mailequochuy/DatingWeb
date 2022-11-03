/**
 * 
 */



Vue.component('noti-modal', {
    data() {
        return {
            show: false
        }
    },
    template: '<div class="modal-header" v-model="show">'
                +'<h5 class= "modal-title" id="exampleModalCenterTitle" > Excellent < i class= "text fas fa-heart" ></i><i class="text fas fa-heart"></i><i class="text fas fa-heart"></i> </h5 >'
                +'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
                    +'<span aria-hidden="true">&times;</span>'
                +'</button>'
                +'</div >'
                +'<div class="modal-body">'
                    +'<i class="notification-icon fas fa-check"></i>'
                    +'<h3 class="text">Match new friend!!! <3</h3>'
                +'</div>',
        
        methods: {
            showNoti: function(noti){
                if(noti == true){
                    
                    this.show = true
                }
            }
        }
    
})
