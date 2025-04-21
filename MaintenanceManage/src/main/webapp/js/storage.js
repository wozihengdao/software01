const storage = {
    set(key, value) {
        try {
            localStorage.setItem(key, JSON.stringify(value))
        } catch (e) {
            console.error('LocalStorage存储失败:', e)
        }
    },

    get(key, defaultValue = null) {
        try {
            const value = localStorage.getItem(key)
            return value ? JSON.parse(value) : defaultValue
        } catch (e) {
            console.error('LocalStorage获取失败:', e)
            return defaultValue
        }
    },

    remove(key) {
        localStorage.removeItem(key)
    },

    clear() {
        localStorage.clear()
    },

    setExpire(key, value, expire) {
        const data = {
            value,
            expire: Date.now() + expire * 60 * 1000
        }
        this.set(key, data)
    },

    getExpire(key) {
        const data = this.get(key)
        if (!data) return null

        if (Date.now() > data.expire) {
            this.remove(key)
            return null
        }
        return data.value
    }
}

export default storage