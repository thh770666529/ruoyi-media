
export default function ({route, req, res, store, next}) {

  if (process.client) {
    store.commit('INIT_WEB');
  }

}
