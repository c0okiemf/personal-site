<template>

    <div
            style="opacity: 0"
            ref="container"
            class="base-view"
            @wheel="handleScroll($event)"
            @touchstart="handleTouchStart($event)"
            @touchend="handleTouchEnd($event)"
            @touchmove.prevent=""
    >
        <transition :name="activeTransition" @before-enter="canScroll = false" @after-leave="canScroll = true">
            <keep-alive>
                <introduction-component
                        v-if="views[currentView] === 'greetings'"
                        @slideDown="slideDown"
                />
                <summary-component
                        v-if="views[currentView] === 'summary'"
                        @slideDown="slideDown"
                        @slideUp="slideUp"
                />
                <feedback-component
                        v-if="views[currentView] === 'feedback'"
                        @slideUp="slideUp"
                />
            </keep-alive>
        </transition>
    </div>

</template>

<script>
    export default {
        data: function () {
            return {
                loaded: false,
                canScroll: true,
                currentView: 0,
                activeTransition: 'cube-next',
                transitionNames: {
                    slideDown: 'cube-next',
                    slideUp: 'cube-prev'
                },
                views: [
                    'greetings',
                    'summary',
                    'feedback'
                ],
                touchStart: undefined
            }
        },
        mounted() {
            this.animateOpacity();
        },
        components: {
            IntroductionComponent: require('./IntroductionComponent.vue').default,
            SummaryComponent: require('./SummaryComponent.vue').default,
            FeedbackComponent: require('./FeedbackComponent.vue').default
        },
        methods: {
            handleScroll(e) {
                if (e.wheelDelta < 0 || e.deltaY > 0) {
                    this.slideDown();
                } else {
                    this.slideUp();
                }
            },
            handleTouchStart(e) {
                this.touchStart = e.touches[0].clientY;
            },
            handleTouchEnd(e) {
                let touchEnd = e.changedTouches[0].clientY;
                if (this.touchStart !== undefined) {
                    if(this.touchStart > touchEnd + 10){
                        this.slideDown();
                    } else if(this.touchStart < touchEnd - 10){
                        this.slideUp();
                    }
                }
            },
            async slideDown() {
                if (this.currentView < this.views.length - 1 && this.canScroll) {
                    this.activeTransition = this.transitionNames.slideDown;
                    await this.$nextTick();
                    this.currentView++;
                }
            },
            async slideUp() {
                if (this.currentView > 0 && this.canScroll) {
                    this.activeTransition = this.transitionNames.slideUp;
                    await this.$nextTick();
                    this.currentView--;
                }
            },
            animateOpacity() {
                let that = this;
                window.addEventListener("load", function(event) {
                    that.$refs.container.animate([
                        {opacity: 0},
                        {opacity: 1}
                    ], {
                        duration: 1000,
                        fill: 'forwards'
                    });
                });
            }
        }
    }
</script>

<style lang="sass" scoped>

$fadeInTime: 5s
$cubeTime: .5s


.base-component
    animation: opacity $fadeInTime
@keyframes opacity
    0%
        opacity: 0
    100%
        opacity: 100%

.cube-next-enter-active
    position: absolute
    width: 100%
    animation: center-bottom $cubeTime reverse
.cube-next-leave-active
    position: absolute
    width: 100%
    animation: center-top $cubeTime

.cube-prev-enter-active
    position: absolute
    width: 100%
    animation: center-top $cubeTime reverse
.cube-prev-leave-active
    position: absolute
    width: 100%
    animation: center-bottom $cubeTime

@keyframes center-top
    0%
        transform: translateY(0%) rotateX(0deg)
    100%
        transform: translateY(-50%) rotateX(90deg)
@keyframes center-bottom
    0%
        transform: translateY(0%) rotateX(0deg)
    100%
        transform: translateY(50%) rotateX(-90deg)


</style>