package ru.petrushin.ya.music.presentation.view.presenter;

import ru.petrushin.ya.music.presentation.view.BaseMainView;
import ru.petrushin.ya.music.presentation.view.navigation.MainRouter;

public abstract class BaseMainPresenter<View extends BaseMainView>
    extends BasePresenter<View, MainRouter> {
}