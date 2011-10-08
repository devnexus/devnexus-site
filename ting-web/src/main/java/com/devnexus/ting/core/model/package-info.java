    /**
     * The Model package contains the model objects.
     */
    @org.hibernate.annotations.GenericGenerators(
        {
        @org.hibernate.annotations.GenericGenerator(
            name="hibseq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000")
            }
         )
         }
    )
    package com.devnexus.ting.core.model;
