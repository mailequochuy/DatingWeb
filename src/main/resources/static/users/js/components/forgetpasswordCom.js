/**
 * 
 */
 
 Vue.component('modal', {
    template: '<div class="modal-header">'
                +'<h5 class="modal-title" id="exampleModalLongTitle">Sent code to your mail, check code!!!</h5>'
                +'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
                  +'<span aria-hidden="true">&times;</span>'
                +'</button>'
              +'</div>'
              +'<div class="modal-body"> ...' 
              +'</div>'
              +'<div class="modal-footer">'
                +'<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>'
               +' <button type="button" class="btn btn-primary">Save changes</button>'
              +'</div>',
    data: function () {
        console.log("### DATA");
    },
})