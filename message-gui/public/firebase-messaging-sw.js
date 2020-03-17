// firebase-messaging-sw.js
importScripts('https://www.gstatic.com/firebasejs/3.6.8/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/3.6.8/firebase-messaging.js');

firebase.initializeApp({
    messagingSenderId: '246171550137'
});

const messaging = firebase.messaging();

// регистрируем свой обработчик уведомлений
messaging.setBackgroundMessageHandler(function(payload) {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    return self.registration.showNotification(payload.notification.title, payload.notification);
});

self.addEventListener('notificationclick', function(event) {
    console.log('notificationclick message', event);
    const arrayActions = event.notification.actions;
    event.notification.close();
    if (arrayActions.length <= 0) arrayActions.push('/');
    arrayActions.forEach(function (notificationAction) {
        const target = notificationAction.action;

        // этот код должен проверять список открытых вкладок и переключатся на открытую
        // вкладку с ссылкой если такая есть, иначе открывает новую вкладку
        event.waitUntil(clients.matchAll({
            type: 'window',
            includeUncontrolled: true
        }).then(function(clientList) {
            // clientList почему-то всегда пуст!?
            for (var i = 0; i < clientList.length; i++) {
                var client = clientList[i];
                if (client.url === target && 'focus' in client) {
                    return client.focus();
                }
            }
            // Открываем новое окно
            return clients.openWindow(target);
        }));
    });
});