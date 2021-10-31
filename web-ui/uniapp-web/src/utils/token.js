import storage from '@/utils/storage'
const ACCESS_TOKEN = 'AccessToken'
const expiryTime = 30 * 86400

export function getTokenKey() {
    return ACCESS_TOKEN
}

export function getToken() {
    return storage.get(ACCESS_TOKEN);
}

export function setToken(token) {
    storage.set(ACCESS_TOKEN, token, expiryTime)
}

export function removeToken() {
    storage.remove(ACCESS_TOKEN)
}
