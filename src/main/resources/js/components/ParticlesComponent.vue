<script>
    const randSign = () => {
        return Math.random() > 0.5 ? 1 : -1;
    };

    export default {
        inject: ['provider'],
        data: function () {
            return {
                fps: 60,
                stepsPerSecond: 20,
                trailLength: 10,
                tagProbability: 0.1,
                hoverDecay: 1, // sec
                size: 20, //%,
                inverted: true,
                usePercent: false,
                fontFace: 'hack, mono-space',
                margin: {
                    x: 1, //%
                    y: 1  //%
                },
                fillStyle: 'rgb(86,255,233, %s)',
                taggedFillStyle: 'rgb(231,245,255, 1)',
                bgColor: 'rgb(0,0,0)',
                hoverFillStyle: 'rgb(255,255,255)',
                hoverRadius: 200,
                characters: 'IGORPICHUGIN',
                interval: null
            }
        },
        props: ['mousePos'],
        beforeDestroy() {
            clearInterval(this.interval);
        },
        render() {
            if(!this.provider.ctx) return;
            const ctx = this.provider.ctx;
            if (this.inverted) {
                ctx.translate(ctx.canvas.width, 0);
                ctx.scale(-1, 1);
            }
            let that = this,
                taggedTrailPos = {},
                nextTaggedTrailPos = {},
                countTillHead = [],
                framesSinceLastStep = 0,
                throttleRate = 1 / ((1 / that.fps) / (1 / that.stepsPerSecond)),
                prevMouseX = 0,
                prevMouseY = 0,
                framesWithoutHover = 0;
            this.interval = setInterval(() => {
                that.initCtx(ctx);
                if (that.mousePos.x === prevMouseX && that.mousePos.y === prevMouseY) {
                    if (framesWithoutHover < that.hoverDecay * that.fps)
                        framesWithoutHover++;
                } else if (framesWithoutHover > 1) {
                    framesWithoutHover--;
                }
                let hoverRadiusSquared =
                    that.hoverRadius * that.hoverRadius / (1 / (that.hoverDecay - framesWithoutHover / that.fps));
                prevMouseX = that.mousePos.x;
                prevMouseY = that.mousePos.y;
                let taggedNext = [],
                    size = (that.usePercent ? that.wC(that.size) : that.size);
                ctx.font = size + 'px ' + that.fontFace;
                for (
                    let x = (size / 2) + that.wC(that.margin.x);
                    x < ctx.canvas.width;
                    x += (size + that.wC(that.margin.x))
                ) {
                    let xI = '' + x;
                    if (countTillHead[xI] === undefined)
                        countTillHead[xI] = 0;
                    let firstY = true;
                    for (
                        let y = 0 - (size + that.hC(that.margin.y));
                        y < ctx.canvas.height;
                        y += (size + that.hC(that.margin.y))
                    ) {
                        ctx.fillStyle = that.bgColor;
                        let index = '' + x + ':' + y;
                        if (
                            firstY
                            && Math.random() < that.tagProbability
                            && taggedTrailPos[index] === undefined
                            && countTillHead[xI] <= 0
                            && framesSinceLastStep >= throttleRate
                        ) {
                            ctx.fillStyle = that.taggedFillStyle;
                            nextTaggedTrailPos[index] = 1;
                            countTillHead[xI] = that.trailLength;
                        } else if (taggedTrailPos[index] !== undefined) {
                            ctx.fillStyle = (taggedTrailPos[index] === 0)
                                ? that.taggedFillStyle
                                : that.fillStyle.replace('%s', (1 / taggedTrailPos[index]).toString());
                            if (framesSinceLastStep >= throttleRate) {
                                nextTaggedTrailPos[index] = (taggedTrailPos[index] < that.trailLength)
                                    ? taggedTrailPos[index] + 1
                                    : undefined;
                            }
                        }
                        let xHover = x;
                        if (this.inverted) {
                            xHover = ctx.canvas.width - xHover;
                        }
                        let distanceSquared =
                            Math.pow(xHover - that.mousePos.x, 2) + Math.pow(y - that.mousePos.y, 2);
                        if (
                            distanceSquared < hoverRadiusSquared
                            && that.mousePos.x !== null
                        ) {
                            ctx.fillStyle = that.hoverFillStyle;
                        }
                        ctx.fillText(that.characters[Math.floor(Math.random() * that.characters.length)], x, y);
                        if (
                            nextTaggedTrailPos[index] === 1
                            && (y + (size + that.wC(that.margin.y))) <= ctx.canvas.height
                        ) {
                            taggedNext.push('' + x + ':' + (y + (size + that.hC(that.margin.y))));
                        }
                        firstY = false;
                    }
                    countTillHead[xI]--;
                }
                taggedTrailPos = nextTaggedTrailPos;
                taggedNext.forEach((i) => {
                    taggedTrailPos[i] = 0;
                });
                if (framesSinceLastStep >= throttleRate) {
                    framesSinceLastStep = 0;
                } else {
                    framesSinceLastStep++;
                }
            }, (1 / that.fps) * 1000);
        },
        methods: {
            wC(percent) {
                return Math.floor((this.provider.ctx.canvas.width / 100) * percent);
            },
            hC(percent) {
                return Math.floor((this.provider.ctx.canvas.height / 100) * percent);
            },
            initCtx(ctx) {
                ctx.clearRect(0, 0, this.wC(100), this.hC(100));
            }
        }
    }
</script>