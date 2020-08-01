<template>
    <div class="feedback-view">
        <div @click="$emit('slideUp')" class="arrow-component-wrapper top">
            <div class="arrow-name">Summary</div>
            <canvas-component :wrapperClass="['arrow-canvas-wrapper']">
                <arrow-component></arrow-component>
            </canvas-component>
        </div>
        <div class="floater">
            <div class="head">Contact me:</div>
            <div class="form" v-if="!sent">
                <input type="text" v-model="name" placeholder="Name">
                <input type="text" v-model="email" placeholder="Email">
                <textarea v-model="message" placeholder="Your message"></textarea>
                <button class="btn-anim" @click="submit">Submit</button>
            </div>
            <div class="done" v-else>
                <div class="message">Thank you for your interest!</div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
            return {
                name: '',
                email: '',
                message: '',
                sent: false
            }
        },
        components: {
            CanvasComponent: require('./CanvasComponent.vue').default,
            ArrowComponent: require('./ArrowComponent.vue').default,
        },
        methods: {
            doneHandler(data, obj) {
                obj.sent = true;
            },
            submit() {
                let that = this;
                fetch('/api/feedback', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    },
                    body: JSON.stringify({
                        name: this.name,
                        email: this.email,
                        message: this.message
                    })
                })
                    .then(res => {
                        return res.json();
                    })
                    .then(data => {
                        that.doneHandler(data, that);
                    })
            },
        }
    }
</script>

<style scoped>

</style>