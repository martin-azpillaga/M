module.exports = {
    title: 'Project M',
    description: 'A data-driven programming language for game developers',
    theme: 'yuu',
    themeConfig: {
        yuu: {
            defaultDarkTheme: true,
        },
        logo: 'https://avatars2.githubusercontent.com/u/6147446?s=400&u=c325f5bfc187e889f05cc872681ad1cb1ba6b3b5&v=4',
        nav: [{
                text: 'Starter guide',
                link: '/0. Starter guide/0. Set up'
            },
            {
                text: 'User guide',
                link: '/1. User guide/0. How to/0. Transforms'
            },
            {
                text: 'Developer guide',
                link: '/2. Developer guide/0. Philosophy/0. Motivation'
            }
        ],
        sidebar: {
            '/0. Starter guide/': [
                '0. Set up',
                '1. Your first project',
                '2. Personalize'
            ],
            '/1. User guide/': [{
                title: 'How to',
                children: [
                    '0. How to/0. Transforms',
                    '0. How to/1. Rendering'
                ]
            }],
            '/2. Developer guide/': [
                '0. Philosophy/0. Motivation',
                '0. Philosophy/2. Design decisions'
            ]

        },
        lastUpdated: 'Last Updated'
    }
}