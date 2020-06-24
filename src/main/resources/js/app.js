import '../sass/main.sass';

window.Vue = require('vue').default;
window.Vue.config.devtools = true;

window.Vue.component('base-component', require('./components/BaseComponent.vue').default);

window.app = new Vue({
    el: '#app'
});