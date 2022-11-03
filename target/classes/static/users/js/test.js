/**
 * 
 */

Vue.component("model-test", {
    template: "#modal-template",
});

new Vue({
    el: '#app',
        data: {
          showModal: false
        }
})