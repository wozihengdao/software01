// config.js
export const API_CONFIG = {
    baseURL: 'http://localhost:7469/MaintenanceManage',
    endpoints: {
        timeSel: {
            update: '/timeSel/update',
            selectAll: '/timeSel/selectAll'
        },
        templates: {
            upkeep: '/upkeepOrder/selectTemplates',
            patrol: '/patrolOrder/selectTemplates',
            check: '/checkOrder/selectTemplates',
            spare: '/spareOrder/selectTemplates'
        }
    }
}