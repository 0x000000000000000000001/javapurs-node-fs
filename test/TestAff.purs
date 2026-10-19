module TestAff where

import Prelude

import Data.Array (filterA)
import Data.Maybe (maybe)
import Data.String.CodeUnits (charAt, singleton)
import Effect (Effect)
import Effect.Aff (Aff, launchAff_)
import Effect.Class (liftEffect)
import Effect.Console (log)
import Node.FS.Aff (stat, readdir)
import Node.FS.Stats (isDirectory)

main :: Effect Unit
main = launchAff_ testAff

-- The JVM port wrapper awaits this action instead of returning immediately
-- after launchAff_.
testAff :: Aff Unit
testAff = do
  files <- readdir "."
  files' <- flip filterA files \file -> do
    stat <- stat file
    pure $ isDirectory stat
      && (maybe false (singleton >>> (_ /= ".")) $ charAt 0 file)
  liftEffect $ log $ show files'
