import Vue from 'vue'
import VueRouter from 'vue-router'
import Products from '@/views/Products'
import ProductDetail from '@/views/ProductDetail'
import AddReview from '@/views/AddReview'
import NotFound from '@/views/NotFound'
import Store from '@/store/index'
import Watcher from '@/views/Watcher'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'products',
    component: Products
  },
  {
    path: '/products/:id',
    name: 'product-detail',
    component: ProductDetail
  },
  {
    path: '/products/:id/add-review',
    name: 'add-review',
    component: AddReview
  },
  {
    path: '/products',
    redirect: { name: 'products' } 
  },
  {
    path: '/watcher',
    name: 'watcher',
    component: Watcher
  },
  {
    path: '*',
    name: 'notFound',
    component: NotFound
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach( (to, from, next) => {
  if (to.name == 'product-detail' || to.name == 'add-review') {
    const id = to.params.id;
    const product = Store.state.products.find( p => {
      return p.id == id;
    })
    if (!product) {
      next( { name: 'products'} )
    }
  }

  next();
});

export default router
