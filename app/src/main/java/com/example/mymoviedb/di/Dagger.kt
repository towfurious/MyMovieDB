import com.example.mymoviedb.model.repository.Repository
import com.example.mymoviedb.model.repository.RepositoryImpl
import com.example.mymoviedb.model.rest.RetrofitClientInstance
import com.example.mymoviedb.ui.MainActivity
import com.example.mymoviedb.ui.list.MovieListFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: MovieListFragment)
}

@Module(includes = [NetworkModule::class, AppBindModule::class])
class AppModule

@Module
class NetworkModule {
    @Provides
    fun provide(): RetrofitClientInstance {
        return RetrofitClientInstance
    }
}

@Module
interface AppBindModule {
    @Suppress("FunctionName")
    @Binds
    fun bindRepositoryImpl_to_Repository(
        repositoryImpl: RepositoryImpl,
    ): Repository
}

