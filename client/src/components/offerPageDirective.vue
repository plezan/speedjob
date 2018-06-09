import Vue from 'vue'
import offerPageStore from './offerPageStore'

Vue.Directive('offerPage', {

    bind(el, binding, offer){
        el.addEventListener('click', function(e){
            e.preventDefault()
            store.state.offer = el.getAttribute('div')
        })
    }
}) 

<!-- https://www.youtube.com/watch?v=J2E1-04NEAM&t=0s&list=PLjwdMgw5TTLW-mAtlR46VajrKs4dep3y0&index=14 -->